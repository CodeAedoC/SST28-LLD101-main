public interface PricingPolicy {
    double amount(BookingRequest req);
}
