package com.hcl.backend.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private String customerName;
    private String status;
    private Double totalAmount;
    private LocalDate orderedAt;
    private List<OrderItemResponseDTO> items;
}