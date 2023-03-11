package com.example.market.service;

import com.example.market.domain.Product;
import com.example.market.repository.JpaProductRepository;
import com.example.market.repository.MemoryProductRepository;
import com.example.market.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {


    EntityManager em;
    ProductService productService;
    ProductRepository productRepository;
    @BeforeEach
    public void beforeEach(){
        //
        //productRepository= new MemoryProductRepository();
        productRepository= new JpaProductRepository(em);
        productService= new ProductService(productRepository);
    }
    @Test
    void test(){
        Product product1 = new Product();
        product1.setName("hello1213");
        product1.setCategory("fruit");
        product1.setPrice(1000);

        //when
        productService.save(product1);

        //then
        Product findProduct = productService.findByName("hello1213").get();
        Assertions.assertThat(product1.getName()).isEqualTo(findProduct.getName());
    }

}
