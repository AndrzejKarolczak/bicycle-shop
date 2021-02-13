package com.example.bicycleshop.backend.entities;

import com.example.bicycleshop.backend.entities.enums.BicycleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = Bicycle.DISCRIMINATOR_VALUE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Bicycle extends Product implements Serializable {
    public static final String DISCRIMINATOR_VALUE = "BICYCLE";

    @Enumerated(EnumType.STRING)
    private BicycleType bicycleType;

    public Bicycle(String name, BigDecimal price, BicycleType bicycleType, BusinessEntity manufacturer) {
        super(name, price, manufacturer);
        this.bicycleType = bicycleType;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
            "productId=" + getProductId() +
            ", name='" + getName() + '\'' +
            ", code='" + getCode() + '\'' +
            ", price=" + getPrice() +
            ", bicycleType=" + bicycleType +
            ", manufacturer=" + getManufacturer() +
            '}';
    }
}
