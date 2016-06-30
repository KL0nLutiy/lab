package database;

/**
 * Created by Vlad on 28.06.2016.
 */
public class OrderInfo {
    private String productId;
    private String name;
    private String price;
    private String orderId;
    private String date;

    public OrderInfo(String productId, String name, String price, String orderId, String date) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.orderId = orderId;
        this.date = date;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }
}
