package com.hcl.backend.dto;

import lombok.Data;

@Data
public class OrderItemResponseDTO {

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double priceAtOrder;
}