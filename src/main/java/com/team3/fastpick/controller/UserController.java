package com.team3.fastpick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team3.fastpick.entity.User;
import com.team3.fastpick.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;

	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String id,
	                    @RequestParam String password,
	                    HttpSession session,
	                    Model model) {
	    User user = userService.login(id, password);
	    if (user != null) {
	        session.setAttribute("loginUser", user);
	        return "redirect:/main-page"; // 로그인 성공 시 이동할 페이지
	    } else {
	        model.addAttribute("error", "가입하지 않은 아이디이거나 잘못된 비밀번호입니다.");
	        return "login"; // 다시 로그인 페이지로
	    }
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 날리기
		return "redirect:/main-page";
	}


}
