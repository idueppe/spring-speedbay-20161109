package io.crowdcode.speedbay.auction.exception;

public class BidTooLowException extends ApplicationException {
    public BidTooLowException() {
    }

    public BidTooLowException(String message) {
        super(message);
    }

    public BidTooLowException(String message, Throwable cause) {
        super(message, cause);
    }

    public BidTooLowException(Throwable cause) {
        super(cause);
    }

    public BidTooLowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
