package com.hcl.backend.controller;

import com.hcl.backend.dto.OrderResponseDTO;
import com.hcl.backend.dto.OrderSummaryResponseDTO;
import com.hcl.backend.dto.PlaceOrderRequestDTO;
import com.hcl.backend.service.OrderService;
import com.hcl.backend.util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // POST /api/orders
    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponseDTO>> placeOrder(
            @Valid @RequestBody PlaceOrderRequestDTO request) {

        OrderResponseDTO response = orderService.placeOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Order placed successfully", response));
    }

    // GET /api/orders
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderSummaryResponseDTO>>> getAllOrders() {
        List<OrderSummaryResponseDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(
                ApiResponse.success("Orders fetched successfully", orders));
    }

    // GET /api/orders/{orderId}
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrderById(
            @PathVariable Long orderId) {

        OrderResponseDTO order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(
                ApiResponse.success("Order fetched successfully", order));
    }
}