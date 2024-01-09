package com.side.bmarket.domain.user.api;

import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.common.jwt.TokenDto;
import com.side.bmarket.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public TokenDto login() {
        return userService.login();
    }
}
