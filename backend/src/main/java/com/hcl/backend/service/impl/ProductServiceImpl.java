package com.hcl.backend.service.impl;

import com.hcl.backend.dto.ProductResponseDTO;
import com.hcl.backend.model.Product;
import com.hcl.backend.repository.ProductRepository;
import com.hcl.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findByIsAvailableTrue();
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category.toUpperCase());
        return products.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setIsAvailable(product.getIsAvailable());
        return dto;
    }
}