package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An object marked with this annotation stores all the information that is needed to register a new {@link Entity}.
 *
 * @see Dto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Dto
public @interface RegisterDto {
}
