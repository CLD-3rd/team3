package com.team3.fastpick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.team3.fastpick.dto.DrawInfo;
import com.team3.fastpick.dto.request.DrawRequest;
import com.team3.fastpick.dto.response.DrawResponse;
import com.team3.fastpick.service.DrawService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DrawController {
	private final DrawService drawService;
	
	@PostMapping("/draw")
    public String draw(@ModelAttribute DrawRequest req, Model model) {
        DrawResponse resp = drawService.requestDraw(req.getPidx(), req.getUidx());
        model.addAttribute("drawResponse", resp);
        return "draw";
    }
	
	@GetMapping("/draw-page")
	public String drawPageForm(Model model) {
		Long pidx = 1L; // 예시로 고정된 상품 ID 사용
		DrawInfo drawInfo = drawService.getDrawInfo(pidx);
		model.addAttribute("drawInfo", drawInfo);
		return "draw-page";
	}
}
