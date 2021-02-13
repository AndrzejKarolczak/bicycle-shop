package com.example.bicycleshop.backend.entities.enums;

import com.example.bicycleshop.backend.entities.Bicycle;
import com.example.bicycleshop.backend.entities.BicyclePart;

public enum ProductType {
    BICYCLE(Bicycle.DISCRIMINATOR_VALUE), PART(BicyclePart.DISCRIMINATOR_VALUE);

    private final String discriminatorValue;

    ProductType(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    public String getDiscriminatorValue() {
        return discriminatorValue;
    }
}
