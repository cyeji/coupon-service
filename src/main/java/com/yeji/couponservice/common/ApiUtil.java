package com.yeji.couponservice.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiUtil {

    public static <T> ApiResponse<T> success(int code, T result, String message) {
        return new ApiResponse<>(true, code, result, message);
    }

    public static <T> ApiResponse error(int code, T result, String message) {
        return new ApiResponse<>(false, code, result, message);
    }

    public static class ApiResponse<T> {

        private boolean success;

        private int code;

        private T result;

        private String message;

        public ApiResponse(boolean success, int code, T result, String message) {
            this.success = success;
            this.code = code;
            this.result = result;
            this.message = message;
        }
    }

}
