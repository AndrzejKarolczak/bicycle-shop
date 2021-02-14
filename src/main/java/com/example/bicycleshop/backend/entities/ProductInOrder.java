package com.example.bicycleshop.backend.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products_in_orders")
@IdClass(ProductInOrderPrimaryKey.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ProductInOrder {
    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Id
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Positive
    @Column(nullable = false)
    private int quantity;
    
    @Positive
    @Column(nullable = false)
    private double price;

    public ProductInOrder(Product product, Order order, int quantity, double price) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
            "orderId=" + order.getOrderId() +
            ", clientId=" + order.getClient().getBusinessEntityId() +
            ", product=" + product +
            ", quantity=" + quantity +
            '}';
    }
}
