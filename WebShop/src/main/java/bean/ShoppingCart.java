package bean;

import entity.OrderItem;
import entity.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -4901687745779053332L;
    private int totalCount = 0;
    private BigDecimal totalCost = BigDecimal.ZERO;
    private Map<Integer, OrderItem> products = new LinkedHashMap<>();

    public void addProduct(Product product) {
        OrderItem orderItem = products.get(product.getId());
        if (orderItem == null) {
            orderItem = new OrderItem(product);
            products.put(product.getId(), orderItem);
        }
        refreshStatistics();
    }

    public int update(int id) {
        OrderItem orderItem = products.get(id);
        return orderItem.getProduct().getProductCost();
    }

    public void removeProduct(int idProduct) {
        OrderItem orderItem = products.get(idProduct);
        if (orderItem != null) {
            products.remove(idProduct);
            refreshStatistics();
        }
    }

    private void refreshStatistics() {
        totalCount = 0;
        totalCost = BigDecimal.ZERO;
        for (OrderItem orderItem : getProducts()) {
            totalCost = totalCost.add(BigDecimal.valueOf(orderItem.getProduct().getProductCost()));
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Collection<OrderItem> getProducts() {
        return products.values();
    }

    public int getSize() {
        return products.values().size();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
