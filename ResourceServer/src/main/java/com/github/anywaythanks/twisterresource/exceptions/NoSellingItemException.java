package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NoSellingItemException extends ItemException {

    public NoSellingItemException() {
        super("The item is not for sale.");
    }
}
