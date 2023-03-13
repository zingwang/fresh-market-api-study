package com.example.market.repository;

import com.example.market.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryProductRepository implements ProductRepository {
    private static Map<Long, Product> store= new HashMap<>();
    private long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        store.put(product.getId(),product);
        return product;
    }

    @Override
    public List<Product> findAllByCategory(String category) {
        return store.values().stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findByName(String name,String category) {
        return store.values().stream().filter(product -> product.getName().equals(name) && product.getCategory().equals(category))
                .findAny();
    }
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }

}