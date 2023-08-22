package com.example.twisterclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoExistAccountException extends RuntimeException {
    public NoExistAccountException() {
        super("Account do not exist.");
    }
}
