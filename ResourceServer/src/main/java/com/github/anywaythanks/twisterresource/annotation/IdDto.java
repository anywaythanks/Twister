package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An object marked with this annotation stores the minimum information that is sufficient to identify the {@link Entity}.
 *
 * @see MappingDto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MappingDto
public @interface IdDto {
}
