package com.example.market.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class BaseResponse<T> {
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private boolean success; // 요청 성공 여부 true, false
    private int code; // 에러코드값
    private String message; // 응답 메세지
    private T data; // 응답 데이터

    public BaseResponse(){
        this.success = true;
        this.code = 0;
    }
    public BaseResponse(boolean success, int code, String message, T data){
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }



}
