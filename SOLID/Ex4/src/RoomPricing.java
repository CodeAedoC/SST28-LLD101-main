import java.util.*;

public class RoomPricing implements PricingPolicy {
    private final Map<Integer, Double> prices = new HashMap<>();

    public RoomPricing() {
        prices.put(LegacyRoomTypes.SINGLE, 14000.0);
        prices.put(LegacyRoomTypes.DOUBLE, 15000.0);
        prices.put(LegacyRoomTypes.TRIPLE, 12000.0);
        prices.put(LegacyRoomTypes.DELUXE, 16000.0);
    }

    @Override
    public double amount(BookingRequest req) {
        return prices.getOrDefault(req.roomType, 16000.0);
    }
}
