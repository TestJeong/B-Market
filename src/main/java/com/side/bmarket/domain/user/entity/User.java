package com.side.bmarket.domain.user.entity;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_nickname")
    private String nickname;


}
