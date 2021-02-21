package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.validation.ValidationMessages;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "product_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public abstract class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", updatable = false)
    private long productId = Long.MIN_VALUE;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = ValidationMessages.NOT_BLANK)
    @Column(nullable = false, length = 36, unique = true)
    private String code = UUID.randomUUID().toString();

    @Positive
    @NotNull(message = ValidationMessages.NOT_NULL)
    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String linkToPicture;

    @Column
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductInOrder> ordersForProduct = new HashSet<>();

    @NotNull(message = ValidationMessages.NOT_NULL)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
        fetch = FetchType.LAZY)
    @JoinColumn(name = "business_entity_id", nullable = false)
    private BusinessEntity manufacturer;

    public Product(String name, BigDecimal price, BusinessEntity manufacturer) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public void addProductToOrder(ProductInOrder orderComposition) {
        if (Objects.nonNull(orderComposition))
            ordersForProduct.add(orderComposition);
    }

    public void removeProductFromOrder(ProductInOrder orderComposition) {
        if (Objects.nonNull(orderComposition))
            ordersForProduct.remove(orderComposition);
    }

    public void printOrderForProduct() {
        System.out.println(ordersForProduct);
    }

    abstract public String getCategoryName();
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getProductId() == Long.MIN_VALUE || getProductId() != product.getProductId()) return false;
        return getCode().equals(product.getCode());
    }

    @Override
    public int hashCode() {
        int result = (int) (getProductId() ^ (getProductId() >>> 32));
        result = 31 * result + getCode().hashCode();
        return result;
    }
}
