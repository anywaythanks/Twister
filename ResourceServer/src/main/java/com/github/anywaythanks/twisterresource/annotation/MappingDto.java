package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An object marked with this annotation can be freely mapped into an {@link Entity}.
 *
 * @see Dto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Dto
public @interface MappingDto {
}
