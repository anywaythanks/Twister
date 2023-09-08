package com.github.anywaythanks.twisterresource.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * An object marked with this annotation cannot be returned by any {@link Service} or {@link Controller}, i.e. he is their argument.
 *
 * @see Dto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Dto
public @interface RequestDto {
}
