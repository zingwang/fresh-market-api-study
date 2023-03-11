package com.example.market.controller;
import com.example.market.common.BaseResponse;
import com.example.market.inteceptor.JwtProvider;
import com.example.market.domain.Product;
import com.example.market.dto.TokenDTO;
import com.example.market.mapper.ProductMapper;
import com.example.market.service.ProductService;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class ProductController {


    private final ProductService productService;

    @Autowired
    private  JwtProvider jwtProvider;

    @Autowired
    private ProductMapper productMapper;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/v1/product/fruit/{name}")
    public int getFruitPrice(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        return 1;
    }

    @GetMapping("/v1/product/fruit/token")
    public String getFruitToken(){
        // 과일 token 획득
        return jwtProvider.createToken("fruit");
    }
    @GetMapping("/v1/product/vegetable/token")
    public String getVegetableToken(){
        // 채소 token 획득
        return jwtProvider.createToken("vegetable");
    }
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/v1/product/fruit/token/check")
    public Claims getTokenCheck( @RequestBody TokenDTO dto){
        // 토큰 검증
        Claims claims = jwtProvider.parseJwtToken("Bearer "+ dto.getToken(),dto.getCategory()); // 토큰 검증
        return claims;
    }
    @GetMapping("/v1/product/init_data")
    public ResponseEntity<BaseResponse> initData() {
        Product product1 = new Product();
        product1.setCategory("fruit");
        product1.setPrice(1000);
        product1.setName("사과");
        productService.save(product1);

        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>( new BaseResponse(true,0,"성공", productService.findAll()) , HttpStatus.OK);
        return rtn;
    }
    /* */
    @GetMapping("/v1/product/fruits")
    public ResponseEntity<BaseResponse> getFruits() {
        List<Product> products= productService.findProductsByCategory("fruit");


        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true,0,"성공",  products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList())), HttpStatus.OK);
        return rtn;
    }
    /* */
    @GetMapping("/v1/product/vegetables")
    public ResponseEntity<BaseResponse> getVegetables() {
        List<Product> products= productService.findProductsByCategory("vegetable");
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true,0,"성공", products.stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList())), HttpStatus.OK);
        return rtn;
    }
    

    @GetMapping("/v1/product/vegetable/{name}")
    public int getVegetablePrice(@PathVariable String name) {
        Optional<Product> product = productService.findByName(name);
        return 1;
    }
}