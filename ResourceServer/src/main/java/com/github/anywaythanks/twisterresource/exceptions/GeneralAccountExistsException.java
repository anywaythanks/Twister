package com.github.anywaythanks.twisterresource.exceptions;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class GeneralAccountExistsException extends UniqueException {
    private final GeneralAccount generalAccount;

    public GeneralAccountExistsException(GeneralAccount generalAccount) {
        this("General Account already exists.", generalAccount);
    }

    public GeneralAccountExistsException(String message, GeneralAccount generalAccount) {
        super(message);
        this.generalAccount = generalAccount;
    }

    public GeneralAccount getGeneralAccount() {
        return generalAccount;
    }
}
