package com.hcl.backend.service.impl;



import com.hcl.backend.dto.*;
import com.hcl.backend.model.Product;
import com.hcl.backend.repository.*;
import com.hcl.backend.model.Order;
import com.hcl.backend.model.OrderItem;
import com.hcl.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderResponseDTO placeOrder(PlaceOrderRequestDTO request) {

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        // Step A — validate stock and build order items
        for (OrderItemRequestDTO itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException(
                            "Product not found with id: " + itemRequest.getProductId()));

            if (!product.getIsAvailable()) {
                throw new RuntimeException(product.getName() + " is not available");
            }

            if (product.getStockQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException(product.getName() + " is out of stock");
            }

            // Step B — deduct stock
            product.setStockQuantity(product.getStockQuantity() - itemRequest.getQuantity());
            productRepository.save(product);

            // Step C — build order item
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPriceAtOrder(product.getPrice());

            totalAmount += product.getPrice() * itemRequest.getQuantity();
            orderItems.add(orderItem);
        }

        // Step D — create and save order
        Order order = new Order();
        order.setCustomerName(request.getCustomerName());
        order.setStatus("CONFIRMED");
        order.setTotalAmount(totalAmount);
        order.setOrderedAt(LocalDate.now());

        // Link each item back to this order
        for (OrderItem item : orderItems) {
            item.setOrder(order);
        }
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return mapToOrderResponseDTO(savedOrder);
    }

    @Override
    public List<OrderSummaryResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToSummaryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        return mapToOrderResponseDTO(order);
    }

    // ── Mappers ──────────────────────────────────────────

    private OrderResponseDTO mapToOrderResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setOrderId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderedAt(order.getOrderedAt());

        List<OrderItemResponseDTO> itemDTOs = order.getItems()
                .stream()
                .map(this::mapToItemResponseDTO)
                .collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    private OrderItemResponseDTO mapToItemResponseDTO(OrderItem item) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPriceAtOrder(item.getPriceAtOrder());
        return dto;
    }

    private OrderSummaryResponseDTO mapToSummaryDTO(Order order) {
        OrderSummaryResponseDTO dto = new OrderSummaryResponseDTO();
        dto.setOrderId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderedAt(order.getOrderedAt());
        dto.setItemCount(order.getItems().size());
        return dto;
    }
}