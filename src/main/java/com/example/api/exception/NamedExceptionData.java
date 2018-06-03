package com.example.api.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class NamedExceptionData {

    private HashMap<String, Object> data;

    /**
     * 예외발생 상세 데이터를 반환한다.
     *
     * @param key
     * @param value
     * @return 예외발생 상세 데이터
     */
    public NamedExceptionData add(String key, Object value) {

        if (this.data == null) {
            this.data = new HashMap<>();
        }
        this.data.put(key, value);

        return this;
    }
}
