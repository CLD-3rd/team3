package com.team3.fastpick.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor  // 빈 생성자

@AllArgsConstructor
public class ProductDto {
    private String name;
    private String imageUrl;
    private Long pidx;
    private Boolean open;
    
    //7월11일 응모여부 버튼 김덕중
    private boolean applied;
}
