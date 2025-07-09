package com.team3.fastpick.service;

import com.team3.fastpick.dto.request.productDto;
import com.team3.fastpick.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<productDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new productDto(p.getName(), p.getImageUrl()))
                .collect(Collectors.toList());
    }
}
