package com.team3.fastpick.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "user")
public class User {

    @Id
    @Column(name = "uidx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uidx;

    private String id;         // 사용자 ID (아이디)
    private String password;   // 비밀번호
    private String name;       // 사용자 이름

    public String getId() {
        return id;
    }
}
