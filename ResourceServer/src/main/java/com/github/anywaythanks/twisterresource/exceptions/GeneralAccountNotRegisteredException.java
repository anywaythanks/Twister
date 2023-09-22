package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GeneralAccountNotRegisteredException extends NotFoundException {
    public GeneralAccountNotRegisteredException() {
        super("General account not registered.");
    }
}
