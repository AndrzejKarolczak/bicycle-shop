package com.example.bicycleshop.frontend;

import com.example.bicycleshop.backend.dao.ProductRepository;
import com.example.bicycleshop.backend.model.Product;
import com.example.bicycleshop.backend.model.ProductAndQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MockedBasketContentService implements IBasketContentService {
    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductAndQuantity> getBasketContents() {
        List<Product> all = repository.findAll();
        return all.stream().map(p -> new ProductAndQuantity(p, 1)).collect(Collectors.toList());
    }
}
