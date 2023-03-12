package com.example.market.service;

import com.example.market.common.BaseResponse;
import com.example.market.domain.Product;
import com.example.market.dto.TokenDTO;
import com.example.market.inteceptor.JwtProvider;
import com.example.market.mapper.ProductMapper;
import com.example.market.repository.ProductRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Transactional
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final JwtProvider jwtProvider;

    private final ProductMapper productMapper ;
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

    public String getToken(String subject){
        return jwtProvider.createToken(subject);
    }
    public Claims checkToken(TokenDTO dto){
       return jwtProvider.parseJwtToken("Bearer "+ dto.getToken(),dto.getCategory()); // 토큰 검증
    }

}




