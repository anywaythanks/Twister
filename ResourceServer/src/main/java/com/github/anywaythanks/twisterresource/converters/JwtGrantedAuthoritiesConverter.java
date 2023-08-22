package com.github.anywaythanks.twisterresource.converters;

import com.github.anywaythanks.twisterresource.configs.SpringAddonsProperties;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.stream.Stream;

public class JwtGrantedAuthoritiesConverter implements Converter<Jwt, Collection<? extends GrantedAuthority>> {
    private final SpringAddonsProperties.IssuerProperties properties;
    private static Logger logger = LoggerFactory.getLogger(JwtGrantedAuthoritiesConverter.class);

    public JwtGrantedAuthoritiesConverter(SpringAddonsProperties.IssuerProperties properties) {
        this.properties = properties;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Collection<? extends GrantedAuthority> convert(Jwt jwt) {
        logger.info(jwt.toString());
        return Stream.of(properties.getClaims()).flatMap(claimProperties -> {
            Object claim;
            try {
                claim = JsonPath.read(jwt.getClaims(), claimProperties.getJsonPath());
            } catch (PathNotFoundException e) {
                claim = null;
            }
            if (claim == null) {
                return Stream.empty();
            }
            if (claim instanceof String claimStr) {
                return Stream.of(claimStr.split(","));
            }
            if (claim instanceof String[] claimArr) {
                return Stream.of(claimArr);
            }
            if (Collection.class.isAssignableFrom(claim.getClass())) {
                final var iter = ((Collection) claim).iterator();
                if (!iter.hasNext()) {
                    return Stream.empty();
                }
                final var firstItem = iter.next();
                if (firstItem instanceof String) {
                    return (Stream<String>) ((Collection) claim).stream();
                }
                if (Collection.class.isAssignableFrom(firstItem.getClass())) {
                    return (Stream<String>) ((Collection) claim).stream().flatMap(colItem -> ((Collection) colItem).stream()).map(String.class::cast);
                }
            }
            return Stream.empty();
        }).map(SimpleGrantedAuthority::new).map(GrantedAuthority.class::cast).toList();
    }
}