package com.team3.fastpick.controller;
import com.team3.fastpick.dto.request.ProductDto;
import com.team3.fastpick.entity.User;
import com.team3.fastpick.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/main-page")
    public String getProductsPage(Model model, HttpSession session) {
    	
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
    
    
    @PostMapping("/apply/{pidx}")
    public String applyToProduct(@PathVariable Long pidx, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 로그인 안 됐으면 로그인 페이지로 이동
            return "redirect:/login";
        }

        // 로그인 되어있으면 응모 처리 진행
        // 예: productService.apply(pidx, loginUser.getUid());
        System.out.println("응모 처리됨: pidx = " + pidx + ", uid = " + loginUser.getUidx());

        return "redirect:/draw-page";
    }
    
    //return html 이름으로 변경 
//    @GetMapping("/draw-page")
//    public String showDrawPage() {
//        return "draw"; // → templates/draw.html로 이동
//    }
}



//// 로그인 여부 확인
//User loginUser = (User) session.getAttribute("loginUser");
//if (loginUser == null) {
//    session.setAttribute("returnTo", "/main-page"); // 이전 경로 저장
//    return "redirect:/login";
//}
