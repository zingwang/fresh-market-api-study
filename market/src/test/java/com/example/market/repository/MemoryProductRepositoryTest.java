package com.example.market.repository;

import com.example.market.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryProductRepositoryTest {

    MemoryProductRepository repository = new MemoryProductRepository() {

    };

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Product product = new Product();
        product.setName("test");
        product.setCategory("fruit");
        repository.save(product);

        Product result= repository.findById(product.getId()).get();
        assertThat(product).isEqualTo(result);
    }

}
