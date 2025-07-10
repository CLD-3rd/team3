package com.team3.fastpick.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {
    private String name;
    private String imageUrl;
    private Long pidx;
    private Boolean open;
}
