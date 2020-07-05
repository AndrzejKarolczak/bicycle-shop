package com.example.bicycleshop.backend.model.enums;

import com.example.bicycleshop.backend.model.Bicycle;
import com.example.bicycleshop.backend.model.BicyclePart;

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
