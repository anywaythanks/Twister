package com.github.anywaythanks.twisterresource.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Annotates a service whose contract is solely for registering/creating a new entity.
 * Similar to Create from Create Read Update Delete (CRUD) or Command from Command-Query Separation (CQS).
 *
 * @see Service
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface RegisterService {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Service.class)
    String value() default "";
}
