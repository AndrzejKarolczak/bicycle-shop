package com.example.bicycleshop.backend.model;

import com.example.bicycleshop.backend.model.enums.BicyclePartType;
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
@DiscriminatorValue(value = BicyclePart.DISCRIMINATOR_VALUE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BicyclePart extends Product implements Serializable {
    public static final String DISCRIMINATOR_VALUE = "PART";

    @Enumerated(EnumType.STRING)
    private BicyclePartType bicyclePartType;

    public BicyclePart(String name, BigDecimal price, BicyclePartType bicyclePartType, BusinessEntity manufacturer) {
        super(name, price, manufacturer);
        this.bicyclePartType = bicyclePartType;
    }

    @Override
    public String toString() {
        return "BicyclePart{" +
            "productId=" + getProductId() +
            ", name='" + getName() + '\'' +
            ", code='" + getCode() + '\'' +
            ", price=" + getPrice() +
            ", bicyclePartType=" + bicyclePartType +
            ", manufacturer=" + getManufacturer() +
            '}';
    }
}
