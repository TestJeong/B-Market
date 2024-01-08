package com.side.bmarket.common.jwt;

import lombok.Builder;
import lombok.Getter;


@Getter
public class TokenDto {
    private final String grantType;
    private final String accessToken;
    private final Long accessTokenExpiresIn;
    private final String refreshToken;

    @Builder
    public TokenDto(String grantType, String accessToken, Long accessTokenExpiresIn, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
    }
}
