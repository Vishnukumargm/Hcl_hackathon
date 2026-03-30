package com.hcl.backend.service;

import com.hcl.backend.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAllProducts();

    List<ProductResponseDTO> getProductsByCategory(String category);
}