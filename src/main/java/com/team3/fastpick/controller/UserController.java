package com.team3.fastpick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	        return "redirect:/main-page";
	    } else {
	        model.addAttribute("error", "가입하지 않은 아이디이거나 잘못된 비밀번호입니다.");
	        return "login";
	        
	    }
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate();
	    return "redirect:/main-page";
	}

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signup(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {

        System.out.println("넘어온 id: " + id);

        try {
            if (userService.isIdDuplicate(id)) {
                return "duplicate";
            }

            User user = User.builder()
                    .id(id)
                    .password(password)
                    .name(name)
                    .build();

            userService.signup(user);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
