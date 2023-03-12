package com.example.market.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success; // 요청 성공 여부 true, false
    private int code; // 에러코드값
    private String message; // 응답 메세지
    private T data; // 응답 데이터

}
