package com.hcl.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PlaceOrderRequestDTO {

    @NotBlank(message = "customerName is required")
    private String customerName;

    @NotEmpty(message = "items cannot be empty")
    private List<OrderItemRequestDTO> items;
}