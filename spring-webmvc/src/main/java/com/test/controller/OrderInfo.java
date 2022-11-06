package com.test.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderInfo {
    @NotBlank
    private String name;
}
