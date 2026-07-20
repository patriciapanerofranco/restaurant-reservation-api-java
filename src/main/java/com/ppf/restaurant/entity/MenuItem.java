package com.ppf.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private BigDecimal price;

    @Setter
    private String category;

    @Setter
    private Boolean active;

    // JPA needs this empty constructor
    public MenuItem() {

    }

    public MenuItem(String name, String description, BigDecimal price, String category, Boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.active = active;
    }

}
