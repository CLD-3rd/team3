package com.team3.fastpick.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team3.fastpick.dto.request.ProductDto;
import com.team3.fastpick.entity.User;
import com.team3.fastpick.service.DrawService;
import com.team3.fastpick.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final DrawService drawService;

    @GetMapping("/main-page")
    public String getProductsPage(Model model, HttpSession session) {
        List<ProductDto> allProducts = productService.getAllProducts();

        // 로그인 여부 체크
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        // 로그인 유저가 응모한 상품 ID 가져오기
        List<Long> appliedProductIds = drawService.getAppliedProductIdsFromRedis(loginUser.getUidx().intValue());

        for (ProductDto product : allProducts) {
            boolean applied = appliedProductIds.contains(product.getPidx());
            product.setApplied(applied);

            Long currentCount = drawService.getCurrentDrawCount(product.getPidx().intValue());

            boolean soldOut = currentCount != null && currentCount >= DrawService.getMaxCount();
            if (soldOut) {
                product.setOpen(false);
            }
        }

        // 진행 중 상품 (Open true)
        List<ProductDto> inProgressProducts = allProducts.stream()
                .filter(ProductDto::getOpen)
                .collect(Collectors.toList());

        // 응모 완료된 상품 (Open false)
        List<ProductDto> completedProducts = allProducts.stream()
                .filter(product -> !product.getOpen())
                .collect(Collectors.toList());

        model.addAttribute("inProgressProducts", inProgressProducts);
        model.addAttribute("completedProducts", completedProducts);

        return "main"; // main.html
    }

    @ResponseBody
    @GetMapping("/product")
    public List<ProductDto> getProductsJson() {
        return productService.getAllProducts();
    }

        @ResponseBody
    @GetMapping("/healthz")
    public String healthCheck() {
        return "OK";
    }
}

}
