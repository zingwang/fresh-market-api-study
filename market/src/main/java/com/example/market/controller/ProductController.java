package com.example.market.controller;

import com.example.market.common.BaseResponse;
import com.example.market.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;
    @Operation(tags = "Fruit", summary = "과일가게 access token 발급", description = "API 호출을 위한 access token을 발급합니다.")
    @GetMapping("/v1/product/token/fruit")
    public ResponseEntity<?>  getFruitToken() {
        // 과일 token 획득
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("accessToken", productService.getToken("fruit"));
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", data) , HttpStatus.OK);
        return rtn;
    }

    @Operation(tags = "Vegetable", summary = "채소가게 access token 발급", description = "API 호출을 위한 access token을 response header로 발급합니다.")
    @GetMapping("/v1/product/token/vegetable")
    public String getVegetableToken(HttpServletResponse response) {
        // 채소 token 획득 Set-Cookie
        response.setHeader("Set-Cookie", "Authorization=" + productService.getToken("vegetable") + "; Path=/; ");
        return "Access token issued on the cookie.";
    }

    @SecurityRequirement(name = "Authorization")
    @Operation(tags = "Fruit", summary = "과일 가격 조회", description = "이름이 지정된 과일의 가격 정보를 조회합니다.\n" +
            "header 내 access token이 요구됩니다.\n" +
            "\n" +
            "토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.")

    @GetMapping("/v1/product/fruit/item")
    public ResponseEntity<?> getFruitPrice(@RequestParam(name = "name", required = false) String name) {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findByName(name,"fruit")), HttpStatus.OK);
        return rtn;
    }
    @SecurityRequirement(name = "Authorization")
    @Operation(tags = "Fruit", summary = "과일 목록 조회", description = "과일 목록을 제공합니다.\n" +
            "header 내 access token이 요구됩니다.")

    @GetMapping("/v1/product/fruit/list")
    public ResponseEntity<?> getFruits() {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findProductsByCategory("fruit")), HttpStatus.OK);
        return rtn;
    }

    @SecurityRequirement(name = "Authorization")
    @Operation(tags = "Vegetable", summary = "채소 목록 조회", description = "채소 목록을 조회합니다.\n" +
            "header 내 access token이 요구됩니다.")
    @GetMapping("/v1/product/vegetable/list")
    public ResponseEntity<?> getVegetables() {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공",productService.findProductsByCategory("vegetable")), HttpStatus.OK);
        return rtn;
    }

    @SecurityRequirement(name = "Authorization")
    @Operation(tags = "Vegetable", summary = "채소 가격 조회", description = "이름이 지정된 채소의 가격을 조회합니다.\n" +
            "header 내 access token이 요구됩니다.\n" +
            "\n" +
            "토큰이 없거나 지정된 이름에 해당하는 정보가 없을 경우 400 응답을 반환합니다.")
    @GetMapping("/v1/product/vegetable/item")
    public ResponseEntity<?> getVegetablePrice(@RequestParam(name = "name", required = false) String name) {
        ResponseEntity<BaseResponse> rtn = new ResponseEntity<>(new BaseResponse(true, 0, "성공", productService.findByName(name,"vegetable")), HttpStatus.OK);
        return rtn;
    }

}