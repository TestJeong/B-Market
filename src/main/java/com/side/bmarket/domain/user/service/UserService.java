package com.side.bmarket.domain.user.service;


import com.side.bmarket.common.jwt.TokenDto;
import com.side.bmarket.common.jwt.TokenProvider;
import com.side.bmarket.domain.user.dto.SignUpRequestDto;
import com.side.bmarket.domain.user.entity.Users;
import com.side.bmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //    유저 회원가입
    public void createUser(SignUpRequestDto signUpRequestDto) {
        Users users = signUpRequestDto.toUser(passwordEncoder);
        userRepository.save(users);
    }

    //    유저 로그인
    public TokenDto login(SignUpRequestDto signUpRequestDto) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        Users user = userRepository.findByUserEmail(signUpRequestDto.getUserEmail())
                .orElseThrow();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signUpRequestDto.getUserEmail(), signUpRequestDto.getPassword());
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성

        return tokenProvider.generateTokenDto(authentication, user);
    }

    //    유저 정보
    public void findByUser() {
    }


}
