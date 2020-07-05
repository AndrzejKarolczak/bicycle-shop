package com.example.bicycleshop.frontend;

import com.example.bicycleshop.backend.model.ProductAndQuantity;

import java.util.List;

public interface IBasketContentService {
    List<ProductAndQuantity> getBasketContents();
}
