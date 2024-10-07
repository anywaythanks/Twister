package com.github.anywaythanks.twisterresource.exceptions;

import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
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

}
