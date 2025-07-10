package com.team3.fastpick.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DrawInfo {
	private LocalDateTime drawTime;
	private String productName;
}
