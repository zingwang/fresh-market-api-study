package com.example.market.inteceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.password}")
    private String secretKey;

    //token 생성//
    public String createToken(String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + Duration.ofMinutes(30L).toMillis()); // 만료시간 30분

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("test") // 토큰발급자(iss)
                .setIssuedAt(now) // 발급시간(iat)
                .setExpiration(expiration) // 만료시간(exp)
                .setSubject(subject) // 토큰 타입: fruit, vegetable
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes())) // 알고리즘, 시크릿 키
                .compact();
    }

    //토큰 유효성 체크//
    public Claims parseJwtToken(String token, String category) {
        try {
            token = BearerRemove(token); // Bearer 제거
            System.out.println("token: " + token);
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();

            if (!claims.getSubject().equals(category)) {
                // category에 맞지 않는 token
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
            }

            return claims;
        } catch (Exception e) {//toekn 인증 실패 시 400
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
    }

    //Bearer 제거//
    private String BearerRemove(String token) {
        return token.substring("Bearer ".length());
    }
}