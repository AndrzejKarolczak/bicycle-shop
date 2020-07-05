package com.example.bicycleshop.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
@AllArgsConstructor
public class ProductAndQuantity {
    private Product product;
    private int quantity;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
