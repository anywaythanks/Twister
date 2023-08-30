package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NicknameUniqueException extends UniqueException {
    public NicknameUniqueException() {
        super("The nickname already exists.");
    }
}
