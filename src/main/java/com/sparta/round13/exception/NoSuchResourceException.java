package com.sparta.round13.exception;

import org.springframework.dao.DataAccessException;

public class NoSuchResourceException extends DataAccessException {

    public NoSuchResourceException() {
        super("리소스가 존재하지 않습니다.");
    }

    public NoSuchResourceException(String msg) {
        super(msg);
    }
}
