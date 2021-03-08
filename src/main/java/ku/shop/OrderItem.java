package ku.shop;

public class OrderItem {
    private int quantity;
    private Product prod;

    public OrderItem(Product prod, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive");

        this.prod = prod;
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return prod.getPrice() * quantity;
    }

    public void checkStock() throws NotEnoughStockException{
        if (this.quantity <= prod.getQuantity()) {
            prod.quantityAfterBuy(quantity);
        }
        else {
            throw new NotEnoughStockException("product not enough!");
        }
    }

}
