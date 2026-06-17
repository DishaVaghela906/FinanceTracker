package SpringBoot.Personal_Finance_Tracker.model.dto;

public class ResponseWrapper<T> {
    private boolean success;
    private String message;
    private T data;
    private long timestamp;

    public ResponseWrapper() {
        this.timestamp = System.currentTimeMillis();
    }

    public ResponseWrapper(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResponseWrapper<T> success(T data, String message) {
        return new ResponseWrapper<>(true, message, data);
    }

    public static <T> ResponseWrapper<T> success(T data) {
        return new ResponseWrapper<>(true, "Request completed successfully", data);
    }

    public static <T> ResponseWrapper<T> error(String message) {
        return new ResponseWrapper<>(false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
