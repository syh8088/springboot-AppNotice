package com.example.api.exception;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class NamedException extends RuntimeException { // FIXME Name과 관련된 예외는 RuntimeException 이 더 적합합니다.

    private final String code; // FIXME Code도 Enum으로 관리하면 더 좋을 것 같네요
    private final HashMap data;

    /**
     * 익셉션 코드를 리턴한다.
     *
     * @param code
     * @param message
     */
    public NamedException(String code, String message) {

        super(message);
        this.code = code;
        this.data = null;
    }

    /**
     * 익셉션 코드와 상세메세지를 리턴한다.
     *
     * @param code
     * @param message
     * @param data
     */
    public NamedException(String code, String message, NamedExceptionData data) {

        super(message);
        this.code = code;
        this.data = data.getData();
    }
}
