package com.side.bmarket.domain.user.support;

import com.side.bmarket.domain.user.entity.Users;
import org.springframework.test.util.ReflectionTestUtils;

public class UserFixture {
    public static Users createUser(String nickname) {
        Users user = Users.builder()
                .nickname(nickname)
                .build();

        ReflectionTestUtils.setField(user,"id", 1L);

        return  user;

    }

}
