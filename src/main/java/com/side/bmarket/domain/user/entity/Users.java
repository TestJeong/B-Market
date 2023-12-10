package com.side.bmarket.domain.user.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_nickname")
    private String nickname;

    @Builder
    public Users(String nickname) {
        this.nickname = nickname;
    }
}
