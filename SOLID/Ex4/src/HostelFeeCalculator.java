import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final List<PricingPolicy> policies;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.policies = List.of(new RoomPricing(), new AddOnPricing());
    }

    public void process(BookingRequest req) {
        double totalMonthly = 0.0;
        for (PricingPolicy policy : policies) {
            totalMonthly += policy.amount(req);
        }

        Money monthly = new Money(totalMonthly);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}
