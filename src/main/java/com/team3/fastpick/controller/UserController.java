package com.team3.fastpick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team3.fastpick.entity.User;
import com.team3.fastpick.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    @ResponseBody
    public String signup(
            @RequestParam("id") String id,
            @RequestParam("password") String password) {

        System.out.println("넘어온 id: " + id);

        try {
            if (userService.isIdDuplicate(id)) {
                return "duplicate";
            }

            User user = User.builder()
                    .id(id)
                    .password(password)
                    .build();

            userService.signup(user);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
