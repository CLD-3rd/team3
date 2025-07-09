package com.team3.fastpick.controller;
import com.team3.fastpick.dto.request.ProductDto;
import com.team3.fastpick.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main-page")
    public String getProductsPage(Model model) {
        List<ProductDto> allProducts = productService.getAllProducts();

        List<ProductDto> inProgressProducts = allProducts.stream()
                .filter(product -> product.getPidx() == 1)
                .collect(Collectors.toList());

        List<ProductDto> completedProducts = allProducts.stream()
                .filter(product -> product.getPidx() != 1)
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
    
    //return html 이름으로 변경 
    @GetMapping("/draw-page")
    public String showDrawPage() {
        return "draw"; // → templates/draw.html로 이동
    }
}

