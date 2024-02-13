package com.side.bmarket.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserDetailDto {
    private final Long userId;
    private final String userNickName;

    public UserDetailDto(Long userId, String userNickName) {
        this.userId = userId;
        this.userNickName = userNickName;
    }
}
