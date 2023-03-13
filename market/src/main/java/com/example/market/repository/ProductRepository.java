package com.example.market.repository;

import com.example.market.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository{

    List<Product> findAll();
    List<Product> findAllByCategory(String category);
    Optional<Product> findByName(String name, String category);
    Optional<Product> findById(Long id);
    Product save(Product product);
}