package com.github.anywaythanks.twisterresource.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestDto
public @interface CreateRequestDto {
}