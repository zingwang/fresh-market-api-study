package com.example.market.repository;

import com.example.market.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Primary
public class JpaProductRepository implements ProductRepository {

    private final EntityManager em;
    @Override
    public Optional<Product> findById(Long id) {
        Product Product= em.find(Product.class,id);
        return Optional.ofNullable(Product);
    }

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }


    @Override
    public List<Product> findAllByCategory(String category) {


        List<Product> result= em.createQuery("select p from Product p where p.category=:category", Product.class)
                .setParameter("category",category)
                .getResultList();

        return result;
    }
    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();

    }
    @Override
    public Optional<Product> findByName(String name, String category) {

        List<Product> result= em.createQuery("select p from Product p where p.name=:name and p.category=:category ", Product.class)
                .setParameter("name",name)
                .setParameter("category",category)
                .getResultList();

        return result.stream().findAny();
    }

}
