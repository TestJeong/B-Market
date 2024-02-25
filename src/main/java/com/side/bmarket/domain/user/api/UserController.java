package com.side.bmarket.domain.user.api;

import com.side.bmarket.common.config.SecurityUtil;
import com.side.bmarket.common.dto.ResponseEntityDto;
import com.side.bmarket.common.jwt.TokenDto;
import com.side.bmarket.domain.user.dto.request.SignUpRequestDto;
import com.side.bmarket.domain.user.dto.response.UserDetailDto;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User 관련 API")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 유저 회원가입
    @Operation(summary = "회원가입", description = "회원가입을 합니다")
    @PostMapping("/user/auth/sign-up")
    public ResponseEntityDto<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        userService.createUser(signUpRequestDto);
        return ResponseEntityDto.of(HttpStatus.OK, "회원가입 완료");
    }

    // 유저 로그인
    @Operation(summary = "로그인", description = "로그인을 합니다")
    @PostMapping("/user/auth/login")
    public TokenDto login(@RequestBody SignUpRequestDto signUpRequestDto) {
        return userService.login(signUpRequestDto);
    }

    // 유저 정보
    @Operation(summary = "유저 정보", description = "유저 정보를 불러옵니다")
    @GetMapping("/user/my")
    public ResponseEntityDto<UserDetailDto> userDetail() {
        return ResponseEntityDto.of(HttpStatus.OK, userService.findByUser(SecurityUtil.getCurrentMemberId()));
    }


}
