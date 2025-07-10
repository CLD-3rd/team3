package com.team3.fastpick.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrawResponse {
    private boolean success;
    private String message;
}