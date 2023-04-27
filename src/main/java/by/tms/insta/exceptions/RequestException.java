package by.tms.insta.exceptions;

public class RequestException extends RuntimeException {
    private String exceptionMessage;
    private Integer errorCode;

    public RequestException(String exceptionMessage, Integer errorCode) {
        this.exceptionMessage = exceptionMessage;
        this.errorCode = errorCode;
    }

    public RequestException(String message, String exceptionMessage, Integer errorCode) {
        super(message);
        this.exceptionMessage = exceptionMessage;
        this.errorCode = errorCode;
    }

    public RequestException(String message, Throwable cause, String exceptionMessage, Integer errorCode) {
        super(message, cause);
        this.exceptionMessage = exceptionMessage;
        this.errorCode = errorCode;
    }

    public RequestException(Throwable cause, String exceptionMessage, Integer errorCode) {
        super(cause);
        this.exceptionMessage = exceptionMessage;
        this.errorCode = errorCode;
    }

    public RequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionMessage, Integer errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionMessage = exceptionMessage;
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "UserDataException{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                ", errorCode=" + errorCode +
                '}';
    }
}
