package com.hcl.backend.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderSummaryResponseDTO {

    private Long orderId;
    private String customerName;
    private String status;
    private Double totalAmount;
    private LocalDate orderedAt;
    private Integer itemCount;
}
