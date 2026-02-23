public class AttendanceRule implements EligibilityRule {
    @Override
    public boolean isEligible(StudentProfile s) {
        return s.attendancePct >= 75;
    }

    @Override
    public String getReason() {
        return "attendance below 75";
    }
}
