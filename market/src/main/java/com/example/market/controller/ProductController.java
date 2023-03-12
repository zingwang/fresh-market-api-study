package com.example.market.controller;

import com.example.market.common.BaseResponse;
import com.example.market.domain.Product;
import com.example.market.dto.TokenDTO;
import com.example.market.service.ProductService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping("/v1/product/fruit/{name}")
    public ResponseEntity<BaseResponse> getFruitPrice(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", product), HttpStatus.OK);
        return rtn;
    }

    @GetMapping("/v1/product/token/fruit")
    public String getFruitToken() {
        // 과일 token 획득
        return productService.getToken("fruit");
    }

    @GetMapping("/v1/product/token/vegetable")
    public String getVegetableToken() {
        // 채소 token 획득
        return productService.getToken("vegetable");
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/v1/product/token/check")
    public Claims getTokenCheck(@RequestBody TokenDTO dto) {
        // 토큰 검증
        Claims claims = productService.checkToken(dto);
        return claims;
    }

    @GetMapping("/v1/product/init_data")
    public ResponseEntity<BaseResponse> initData() {
        Product product1 = new Product();
        product1.setCategory("fruit");
        product1.setPrice(1000);
        product1.setName("사과");
        productService.save(product1);

        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findAll()), HttpStatus.OK);
        return rtn;
    }

    @GetMapping("/v1/product/fruit/list")
    public ResponseEntity<BaseResponse> getFruits() {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findProductsByCategory("fruit")), HttpStatus.OK);
        return rtn;
    }

    @GetMapping("/v1/product/vegetable/list")
    public ResponseEntity<BaseResponse> getVegetables() {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findProductsByCategory("vegetable")), HttpStatus.OK);
        return rtn;
    }

    @GetMapping("/v1/product/vegetable/{name}")
    public ResponseEntity<BaseResponse> getVegetablePrice(@PathVariable String name) {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findByName(name)), HttpStatus.OK);
        return rtn;
    }
}