package com.team3.fastpick.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.team3.fastpick.dto.request.ProductDto;
import com.team3.fastpick.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(p.getName(), p.getImageUrl(), p.getPidx(), p.getOpen()))
                .collect(Collectors.toList());
    }
}
