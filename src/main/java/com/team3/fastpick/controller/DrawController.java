package com.team3.fastpick.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team3.fastpick.dto.request.DrawRequest;
import com.team3.fastpick.dto.response.DrawResponse;
import com.team3.fastpick.service.DrawService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DrawController {
	private final DrawService drawService;
	
	@PostMapping("/draw")
    public ResponseEntity<DrawResponse> draw(@RequestBody DrawRequest req) {
        DrawResponse resp = drawService.requestDraw(req.getPidx(), req.getUidx());
        if (!resp.isSuccess()) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(resp);
        }
        return ResponseEntity.ok(resp);
    }
}
