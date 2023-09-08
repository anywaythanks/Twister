package com.github.anywaythanks.twisterresource.annotation;

import java.lang.annotation.*;

/**
 * Data Transfer Object (DTO). The annotated object stores only the state, and access to this state is always available, one way or another.
 * And this is a de facto immutable object.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dto {
}