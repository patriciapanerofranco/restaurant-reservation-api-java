package com.ppf.restaurant.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MenuItemResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Boolean active;

    public MenuItemResponse(Long id, String name, String description, BigDecimal price, String category, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.active = active;
    }
}