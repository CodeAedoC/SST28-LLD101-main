import java.util.*;

public class AddOnPricing implements PricingPolicy {
    private final Map<AddOn, Double> prices = new EnumMap<>(AddOn.class);

    public AddOnPricing() {
        prices.put(AddOn.MESS, 1000.0);
        prices.put(AddOn.LAUNDRY, 500.0);
        prices.put(AddOn.GYM, 300.0);
    }

    @Override
    public double amount(BookingRequest req) {
        double total = 0.0;
        for (AddOn a : req.addOns) {
            total += prices.getOrDefault(a, 0.0);
        }
        return total;
    }
}
