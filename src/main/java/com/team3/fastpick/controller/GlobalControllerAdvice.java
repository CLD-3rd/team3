package com.team3.fastpick.controller;

import com.team3.fastpick.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ModelAttribute("loginUser")
    public User loginUser(HttpSession session) {
        return (User) session.getAttribute("loginUser");
    }
}