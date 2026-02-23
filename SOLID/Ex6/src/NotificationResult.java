public class NotificationResult {
    public final boolean success;
    public final String error;

    public NotificationResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public static NotificationResult ok() {
        return new NotificationResult(true, null);
    }

    public static NotificationResult fail(String msg) {
        return new NotificationResult(false, msg);
    }
}
