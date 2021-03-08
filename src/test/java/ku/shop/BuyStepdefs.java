package ku.shop;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;

    @Before
    public void setup() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} exists")
    public void a_product_with_price_exists(String name, double price) {
        catalog.addProduct(name, price);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Given("a product {string} for {int} in stock with price {double} for each product")
    public void a_product_for_in_stock_with_price_for_each_product(String name, int stock, double price){
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {int} {string} that more than stock")
    public void I_buy_with_quantity_that_more_than_stock(int quantity, String name) throws NotEnoughStockException {
        Product prod = catalog.getProduct(name);
        OrderItem orderItem = new OrderItem(prod, quantity);
        assertThrows(NotEnoughStockException.class,orderItem::checkStock);
    }

    @When("I buy {int} {string} that less than stock")
    public void I_buy_with_quantity_less_more_than_stock(int quantity, String name) throws NotEnoughStockException {
        Product prod = catalog.getProduct(name);
        OrderItem orderItem = new OrderItem(prod, quantity);
        orderItem.checkStock();
    }

    @Then("There are {int} {string} in stock")
    public void there_are_in_stock(int quantity, String name) {
        Product product = catalog.getProduct(name);
        assertEquals(quantity, product.getQuantity());
    }
}

