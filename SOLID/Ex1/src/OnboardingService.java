import java.util.*;

public class OnboardingService {
    private final StudentRepository repository;
    private final InputParser parser = new InputParser();
    private final StudentValidator validator = new StudentValidator();
    private final ResultPrinter printer = new ResultPrinter();

    public OnboardingService(StudentRepository repository) {
        this.repository = repository;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        Map<String, String> kv = parser.parse(raw);
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors = validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repository.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        repository.save(rec);

        printer.printSuccess(id, repository.count(), rec);
    }
}
