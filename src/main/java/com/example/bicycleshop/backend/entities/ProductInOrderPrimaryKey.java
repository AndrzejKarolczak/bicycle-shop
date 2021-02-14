package com.example.bicycleshop.backend.entities;

import java.io.Serializable;

public class ProductInOrderPrimaryKey implements Serializable {
    private static final long serialVersionUID = 9086627167426987610L;

    private Long product;

    private Long order;

    protected ProductInOrderPrimaryKey() {
    }

    public ProductInOrderPrimaryKey(Product product, Order order) {
        this.product = product.getProductId();
        this.order = order.getOrderId();
    }

    public Long getProduct() {
        return product;
    }

    public Long getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductInOrderPrimaryKey that = (ProductInOrderPrimaryKey) o;

        if (getProduct() != null ? !getProduct().equals(that.getProduct()) : that.getProduct() != null) return false;
        return getOrder() != null ? getOrder().equals(that.getOrder()) : that.getOrder() == null;
    }

    @Override
    public int hashCode() {
        int result = getProduct() != null ? getProduct().hashCode() : 0;
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderCompositionKey{" +
            "product=" + product +
            ", order=" + order +
            '}';
    }
}
