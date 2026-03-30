package com.hcl.backend.service;




import com.hcl.backend.dto.OrderSummaryResponseDTO;
import com.hcl.backend.dto.PlaceOrderRequestDTO;
import com.hcl.backend.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO placeOrder(PlaceOrderRequestDTO request);

    List<OrderSummaryResponseDTO> getAllOrders();

    OrderResponseDTO getOrderById(Long orderId);
}