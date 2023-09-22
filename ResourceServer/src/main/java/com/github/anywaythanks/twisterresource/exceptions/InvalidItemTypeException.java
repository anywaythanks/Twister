package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InvalidItemTypeException extends ItemException {
    public InvalidItemTypeException() {
        super("Invalid type specified.");
    }
}
