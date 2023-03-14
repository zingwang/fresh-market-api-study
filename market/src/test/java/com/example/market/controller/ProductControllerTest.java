package com.example.market.controller;

import com.example.market.domain.Product;
import com.example.market.inteceptor.JwtProvider;
import com.example.market.mapper.ProductMapper;
import com.example.market.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtProvider jwtProvider;
    @MockBean
    private ProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetFruitToken() throws Exception {

        //given
        String token = "fruit_token";
        when(productService.getToken("fruit")).thenReturn(token);

        //when
        mockMvc.perform(get("/v1/product/token/fruit")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.message").value("성공"))
                .andExpect(jsonPath("$.data.accessToken").value(token));

        //then
        verify(productService, times(1)).getToken("fruit");
    }

    @Test
    void testGetFruits() throws Exception {
        //given
        String token = "fruit_token";
        List<Product> productList = new ArrayList<>();
        productList.add( new Product(1L,"사과","fruit",1500));
        productList.add( new Product(2L,"토마토","fruit",2500));

        //when
        when(productService.getToken("vegetable")).thenReturn(token);

        mockMvc.perform(get("/v1/product/fruit/list")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0)
                );

        //then
        verify(productService, times(1)).findProductsByCategory("fruit");
    }

    @Test
    void testgetFruitPrice() throws Exception {
        //given
        String fruitName = "사과";
        String token = "fruit_token";
        Product product = new Product(1L,fruitName,"fruit",1500);
        when(productService.getToken("fruit")).thenReturn(token);

        //when
        mockMvc.perform(get("/v1/product/fruit/item")
                        .header("Authorization", token)
                        .param("name", fruitName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0));

        //then
        verify(productService, times(1)).findByName(fruitName, "fruit");
    }

    @Test
    void testGetVegetableToken() throws Exception {

        //given
        String token = "vegetable_token";
        when(productService.getToken("vegetable")).thenReturn(token);


        //when
        mockMvc.perform(get("/v1/product/token/vegetable"))
                .andExpect(status().isOk())
                .andExpect(content().string("Access token issued on the cookie."));

        //then
        verify(productService, times(1)).getToken("vegetable");
    }


    @Test
    void testGetVegetables() throws Exception {

        //given
        String token = "vegetable_token";
        List<Product> productList = new ArrayList<>();
        productList.add( new Product(1L,"치커리","vegetable",2000));
        productList.add( new Product(2L,"당근","vegetable",1500));

        //when
        when(productService.getToken("vegetable")).thenReturn(token);

        mockMvc.perform(get("/v1/product/vegetable/list")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0)
                );

        //then
        verify(productService, times(1)).findProductsByCategory("vegetable");
    }

    @Test
    void testGetVegetablePrice() throws Exception {

        //given
        String vegetableName = "치커리";
        String token = "fruit_token";
        Product product = new Product(1L,vegetableName,"vegetable",2000);
        when(productService.getToken("fruit")).thenReturn(token);

        //when
        mockMvc.perform(get("/v1/product/vegetable/item")
                        .param("name", vegetableName)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.code").value(0));

        //then
        verify(productService, times(1)).findByName(vegetableName, "vegetable");
    }
}