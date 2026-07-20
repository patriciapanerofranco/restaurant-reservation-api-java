package com.ppf.restaurant.dto;

import lombok.Getter;

@Getter
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;

    public CustomerResponse(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}