package com.side.bmarket.domain.user.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.common.jwt.TokenDto;
import com.side.bmarket.domain.user.dto.request.SignUpRequestDto;
import com.side.bmarket.domain.user.dto.response.UserDetailDto;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 회원가입
    @PostMapping("/auth/sign-up")
    public ResponseEntityDto<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.createUser(signUpRequestDto);
        return ResponseEntityDto.of(HttpStatus.OK, "회원가입 완료");
    }

    // 유저 로그인
    @PostMapping("/auth/login")
    public TokenDto login(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.login(signUpRequestDto);
    }

    // 유저 정보
    @GetMapping("/my")
    public UserDetailDto userDetail() {
        Users user = userService.findByUser(SecurityUtil.getCurrentMemberId());
        return new UserDetailDto(user.getId(), user.getNickname());
    }
}
