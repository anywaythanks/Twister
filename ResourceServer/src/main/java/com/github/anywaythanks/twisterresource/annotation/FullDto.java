package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An object marked with this annotation stores all possible information from the {@link Entity}.
 *
 * @see MappingDto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MappingDto
public @interface FullDto {
}
