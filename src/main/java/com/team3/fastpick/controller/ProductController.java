package com.team3.fastpick.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team3.fastpick.dto.request.ProductDto;
import com.team3.fastpick.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main-page")
    public String getProductsPage(Model model, HttpSession session) {
        List<ProductDto> allProducts = productService.getAllProducts();
        
        List<ProductDto> inProgressProducts = allProducts.stream()
                .filter(product -> product.getOpen())
                .collect(Collectors.toList());

        List<ProductDto> completedProducts = allProducts.stream()
                .filter(product -> !product.getOpen())
                .collect(Collectors.toList());
//        User user = (User) session.getAttribute("loginUser");
//        
        model.addAttribute("inProgressProducts", inProgressProducts);
        model.addAttribute("completedProducts", completedProducts);
        
        
        return "main"; // main.html
    }

    @ResponseBody
    @GetMapping("/product")
    public List<ProductDto> getProductsJson() {
        return productService.getAllProducts();
    }
    
//    @PostMapping("/apply/{pidx}")
//    public String applyToProduct(@PathVariable Long pidx, HttpSession session) {
//        User loginUser = (User) session.getAttribute("loginUser");
//
//        if (loginUser == null) {
//            // 로그인 안 됐으면 로그인 페이지로 이동
//            return "redirect:/login";
//        }
//
//        // 로그인 되어있으면 응모 처리 진행
//        System.out.println("응모 처리됨: pidx = " + pidx + ", uid = " + loginUser.getUidx());
//
//        return "redirect:/draw-page";
//    }
}

