package com.github.anywaythanks.twisterresource.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * An object marked with this annotation cannot be an argument to either {@link Service} or {@link Controller}, i.e. it is their return value.
 *
 * @see Dto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Dto
public @interface ResponseDto {
}
