package com.example.market.inteceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class VegetableCheckInterceptor implements HandlerInterceptor {
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("VegetableCheckInterceptor - preHandle");
        log.info("##### prehandler request.Header session: " + request.getHeader("accessToken") + "subject:vegetable");
        jwtProvider.parseJwtToken("Bearer " + request.getHeader("accessToken"), "vegetable");
        return true;
    }
}
