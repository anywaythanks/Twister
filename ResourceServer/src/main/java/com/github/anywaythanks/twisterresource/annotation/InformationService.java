package com.github.anywaythanks.twisterresource.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * Annotates a service whose contract is solely to return information about an entity.
 * Similar to Read from Create Read Update Delete (CRUD) or Query from Command-Query Separation (CQS).
 *
 * @see Service
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface InformationService {
    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Service.class)
    String value() default "";
}
