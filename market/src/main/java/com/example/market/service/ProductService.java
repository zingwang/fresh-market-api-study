package com.example.market.service;

import com.example.market.domain.Product;
import com.example.market.dto.ProductDTO;
import com.example.market.dto.TokenDTO;
import com.example.market.inteceptor.JwtProvider;
import com.example.market.mapper.ProductMapper;
import com.example.market.repository.ProductRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
    public List<String> findProductsByCategory(String category){
        return productMapper.toList(productRepository.findAllByCategory(category));
    }
    public Product save(Product product){
        return  productRepository.save(product) ;
    }

    public ProductDTO findByName(String productName, String category){

        Optional<Product> product = productRepository.findByName(productName, category);
        if (!product.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
        }
        //productMapper.toDTO(product);
        return productMapper.toDTO(product);
    }

    public String getToken(String subject){
        return jwtProvider.createToken(subject);
    }
    public Claims checkToken(TokenDTO dto){
       return jwtProvider.parseJwtToken("Bearer "+ dto.getToken(),dto.getCategory()); // 토큰 검증
    }

}




