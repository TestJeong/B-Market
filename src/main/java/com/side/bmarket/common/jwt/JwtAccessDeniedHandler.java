package com.side.bmarket.common.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.side.bmarket.common.dto.ErrorDto;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        final ObjectMapper objectMapper = new ObjectMapper();

        // 필요한 권한이 없이 접근하려 할때 403
        ErrorDto errorDto = new ErrorDto(HttpServletResponse.SC_FORBIDDEN, "해당 권한이 없습니다.");

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(objectMapper.writeValueAsString(errorDto));
    }
}
