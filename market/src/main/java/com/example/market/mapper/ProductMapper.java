package com.example.market.mapper;

import com.example.market.domain.Product;
import com.example.market.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductMapper {
    public ProductDTO toDTO(Optional<Product> product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.get().getName());
        productDTO.setPrice(product.get().getPrice());
        return productDTO;
    }

    public List<String> toList(List<Product> productList){
        List<String> nameList = new ArrayList<>();
        for (Product product : productList) {
            nameList.add(product.getName());
        }
        Collections.sort(nameList);
        return nameList;

    }


}