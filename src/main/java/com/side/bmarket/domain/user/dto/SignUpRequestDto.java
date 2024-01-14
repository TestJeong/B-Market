package com.side.bmarket.domain.user.dto;

import com.side.bmarket.domain.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    private  String userEmail;
    private String password;
    private String userNickName;

    public Users toUser(PasswordEncoder passwordEncoder) {
        return Users.builder()
                .userEmail(userEmail)
                .password(passwordEncoder.encode(password))
                .nickname(userNickName)
                .build();
    }

}
