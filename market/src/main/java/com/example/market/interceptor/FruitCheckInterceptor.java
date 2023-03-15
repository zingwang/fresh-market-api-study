package com.example.market.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class FruitCheckInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("FruitCheckInterceptor - preHandle");
        log.info("##### prehandler request.Header session: " + request.getHeader("Authorization") + "subject:fruit");
        jwtProvider.parseJwtToken("Bearer " + request.getHeader("Authorization"), "fruit");
        return true;
    }
}
