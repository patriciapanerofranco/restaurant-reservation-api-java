package com.ppf.restaurant.dto;

import java.math.BigDecimal;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getActive() {
        return active;
    }
}