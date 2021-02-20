package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.backend.entities.enums.OrderStatus;
import com.example.bicycleshop.utilities.Formatter;
import com.example.bicycleshop.validation.ValidationMessages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false)
    private long orderId = Long.MIN_VALUE;

    @CreationTimestamp
    @Column(name = "order_timestamp", nullable = false, updatable = false)
    private LocalDateTime orderTimestamp; //= LocalDateTime.now();

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime lastChangeTimestamp;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity client;

    @NotNull(message = ValidationMessages.NOT_NULL)
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        mappedBy = "order", fetch = FetchType.LAZY)
    private Set<ProductInOrder> productsInOrder = new HashSet<>();

    public Order(BusinessEntity client, OrderStatus orderStatus) {
        this.client = client;
        this.orderStatus = orderStatus;
    }

    public void addProductToOrder(ProductInOrder orderComposition) {
        if (Objects.nonNull(orderComposition))
            this.productsInOrder.add(orderComposition);
    }

    public void removeProductFromOrder(ProductInOrder orderComposition) {
        if (Objects.nonNull(orderComposition))
            this.productsInOrder.remove(orderComposition);
    }

    public void printOrderComposition() {
        System.out.println(productsInOrder);
    }

    public BigDecimal calculateOrderValue() {
        BigDecimal sumOfProductsInOrder = new BigDecimal(0);

        if (Objects.nonNull(productsInOrder))
            for (ProductInOrder p : productsInOrder) {
                BigDecimal quantity = new BigDecimal(p.getQuantity());
                sumOfProductsInOrder = sumOfProductsInOrder.add(p.getProduct().getPrice().multiply(quantity));
            }

        return sumOfProductsInOrder;
    }

    public String getOrderTimestampAsFormattedString(){
        return Formatter.getDateTimeFormatter().format(orderTimestamp);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getOrderId() == Long.MIN_VALUE || getOrderId() != order.getOrderId()) return false;
        if (!getOrderTimestamp().equals(order.getOrderTimestamp())) return false;
        return getClient() != null ? getClient().equals(order.getClient()) : order.getClient() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getOrderId() ^ (getOrderId() >>> 32));
        result = 31 * result + getOrderTimestamp().hashCode();
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
            "orderId=" + orderId +
            ", orderTimestamp=" + orderTimestamp +
            ", client=" + client.getBusinessEntityId() +
            '}';
    }
}
