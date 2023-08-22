package com.github.anywaythanks.twisterresource.exceptions.handler;

import com.github.anywaythanks.twisterresource.exceptions.GeneralAccountExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class ExceptionHandlingController {
    @ExceptionHandler(GeneralAccountExistsException.class)
    public ResponseEntity<GeneralAccountExistsException> alreadyExists(GeneralAccountExistsException generalAccountExistsException) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .location(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{name}")
                        .buildAndExpand(generalAccountExistsException
                                .getGeneralAccount()
                                .getName()
                                .getName()).toUri())
                .body(generalAccountExistsException);
    }
}
