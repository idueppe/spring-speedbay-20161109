package io.crowdcode.speedbay.auction.exception;

public class AuctionNotFoundException extends ApplicationException {
    public AuctionNotFoundException() {
    }

    public AuctionNotFoundException(String message) {
        super(message);
    }

    public AuctionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuctionNotFoundException(Throwable cause) {
        super(cause);
    }

    public AuctionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
