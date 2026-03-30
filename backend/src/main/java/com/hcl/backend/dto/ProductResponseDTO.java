package com.hcl.backend.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {

    private Long productId;
    private String name;
    private String category;
    private Double price;
    private Integer stockQuantity;
    private Boolean isAvailable;
}