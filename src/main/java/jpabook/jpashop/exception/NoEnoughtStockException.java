package jpabook.jpashop.exception;

public class NoEnoughtStockException extends RuntimeException{

    public NoEnoughtStockException() {
         super();
    }

    public NoEnoughtStockException(String message) {
        super(message);
    }

    public NoEnoughtStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEnoughtStockException(Throwable cause) {
        super(cause);
    }

    protected NoEnoughtStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
