package com.example.market.service;

import com.example.market.common.BaseResponse;
import com.example.market.domain.Product;
import com.example.market.mapper.ProductMapper;
import com.example.market.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Transactional
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return  productRepository.findAll();
    }
    public List<Product> findProductsByCategory(String category){
        return  productRepository.findAllByCategory(category);
    }
    public Product save(Product product){
        return  productRepository.save(product) ;
    }


    public Optional<Product> findByName(String productName){
        return productRepository.findByName(productName);
    }
}




