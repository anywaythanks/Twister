package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An object marked with this annotation is used to create new {@link Entity}.
 *
 * @see RequestDto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestDto
public @interface CreateRequestDto {
}
