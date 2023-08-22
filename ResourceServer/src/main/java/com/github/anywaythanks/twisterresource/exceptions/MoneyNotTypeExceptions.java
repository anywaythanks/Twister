package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MoneyNotTypeExceptions extends RuntimeException {
    public MoneyNotTypeExceptions() {
        super("Invalid type specified.");
    }
}
