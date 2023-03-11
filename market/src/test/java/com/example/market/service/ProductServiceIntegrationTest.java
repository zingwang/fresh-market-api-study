package com.example.market.service;

import com.example.market.domain.Product;
import com.example.market.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ProductServiceIntegrationTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    void join() {
        //given
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("hello1213");
        product1.setCategory("fruit");
        product1.setPrice(1000);

        //when
        productService.save(product1);

        //then
        Product findProduct = productService.findByName("hello1213").get();
        Assertions.assertThat(product1.getName()).isEqualTo(findProduct.getName());
    }
    @Test
    public void duplicateTest(){
        //given

    }


}