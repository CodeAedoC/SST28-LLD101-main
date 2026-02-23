public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String error;

    public ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = true;
        this.error = null;
    }

    public static ExportResult failure(String msg) {
        return new ExportResult(null, null, false, msg);
    }

    private ExportResult(String contentType, byte[] bytes, boolean success, String error) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.success = success;
        this.error = error;
    }
}
