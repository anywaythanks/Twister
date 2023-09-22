package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NotEnoughItemsException extends RuntimeException {
    public NotEnoughItemsException() {
        super("Not enough items in inventory.");
    }
}
