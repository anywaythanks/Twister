package com.github.anywaythanks.twisterresource.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InsufficientFundsException extends AccountException {
    public InsufficientFundsException() {
        super("Insufficient funds on the account.");
    }
}
