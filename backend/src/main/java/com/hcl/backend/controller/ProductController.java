package com.hcl.backend.controller;

import com.hcl.backend.dto.ProductResponseDTO;
import com.hcl.backend.service.ProductService;
import com.hcl.backend.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // GET /api/products
    // GET /api/products?category=PIZZA
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProducts(
            @RequestParam(required = false) String category) {

        List<ProductResponseDTO> products;

        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }

        return ResponseEntity.ok(
                ApiResponse.success("Products fetched successfully", products)
        );
    }
}