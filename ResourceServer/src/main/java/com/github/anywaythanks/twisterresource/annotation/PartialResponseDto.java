package com.github.anywaythanks.twisterresource.annotation;

import jakarta.persistence.Entity;

import java.lang.annotation.*;

/**
 * An entity marked with this annotation contains all non-technical information about the {@link Entity}.
 *
 * @see ResponseDto
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseDto
public @interface PartialResponseDto {
}
