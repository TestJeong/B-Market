package com.side.bmarket.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.side.bmarket.common.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        ErrorDto errorDto = new ErrorDto(HttpServletResponse.SC_UNAUTHORIZED, "만료된 JWT 토큰입니다.");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(errorDto));
    }
}
