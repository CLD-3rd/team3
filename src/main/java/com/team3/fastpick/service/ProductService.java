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
    private final DrawService drawService;
    
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(p.getName(), p.getImageUrl(), p.getPidx(), p.getOpen(),false))
                .collect(Collectors.toList());
            
    }
    public List<ProductDto> getProductsWithApplyStatus(Long userId) {
        List<ProductDto> allProducts = getAllProducts();

        // Redis에서 내가 응모한 상품 ID 리스트 가져옴
        List<Long> appliedProductIds = drawService.getAppliedProductIdsFromRedis(userId.intValue());

        return allProducts.stream()
            .peek(product -> {
                boolean isApplied = appliedProductIds.contains(product.getPidx());
                product.setApplied(isApplied);  // ✅ Dto에 applied 필드 있으면 OK
            })
            .collect(Collectors.toList());
    }
    
    
    
}
