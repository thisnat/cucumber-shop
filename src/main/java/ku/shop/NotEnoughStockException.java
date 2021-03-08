package ku.shop;

public class NotEnoughStockException extends RuntimeException{

    public NotEnoughStockException(String err) {
        super(err);
    }

}
