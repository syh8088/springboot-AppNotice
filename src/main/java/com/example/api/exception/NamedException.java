package com.example.api.exception;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class NamedException extends Exception {

    private final String code;
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
