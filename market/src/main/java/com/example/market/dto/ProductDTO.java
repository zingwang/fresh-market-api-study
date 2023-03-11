package com.example.market.dto;

public class ProductDTO {
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    private String name;
    private Integer price;
    // getter and setter
}