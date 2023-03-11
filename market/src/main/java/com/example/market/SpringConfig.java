package com.example.market;

import com.example.market.repository.JpaProductRepository;
import com.example.market.repository.MemoryProductRepository;
import com.example.market.repository.ProductRepository;
import com.example.market.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig
{
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public ProductService productService(){
        return new ProductService(memberRepository());
    }
    @Bean
    public ProductRepository memberRepository() {

        return new JpaProductRepository(em);
        //return new MemoryProductRepository();
    }


}
