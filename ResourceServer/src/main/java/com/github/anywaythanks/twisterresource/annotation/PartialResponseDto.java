package com.github.anywaythanks.twisterresource.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseDto
public @interface PartialResponseDto {
}
