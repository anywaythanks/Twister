package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidMoneyTypeException extends RuntimeException {
    public InvalidMoneyTypeException() {
        super("Invalid type specified.");
    }
}
