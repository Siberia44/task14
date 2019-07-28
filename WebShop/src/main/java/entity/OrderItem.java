package entity;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 3305300532571071231L;
    private Product product;

    public OrderItem(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}
