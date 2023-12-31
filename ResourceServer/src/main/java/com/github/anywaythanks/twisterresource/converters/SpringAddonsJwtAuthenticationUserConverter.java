package com.github.anywaythanks.twisterresource.converters;

import com.github.anywaythanks.twisterresource.configs.AuthorizeServerProperties;
import com.github.anywaythanks.twisterresource.models.auth.AuthorizationTokenUser;
import com.github.anywaythanks.twisterresource.models.auth.UserPrincipal;
import com.jayway.jsonpath.JsonPath;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SpringAddonsJwtAuthenticationUserConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final AuthorizeServerProperties springAddonsProperties;

    public SpringAddonsJwtAuthenticationUserConverter(AuthorizeServerProperties springAddonsProperties) {
        this.springAddonsProperties = springAddonsProperties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        final var issuerProperties = springAddonsProperties.get(jwt.getIssuer());
        final var authorities = new JwtGrantedAuthoritiesConverter(issuerProperties).convert(jwt);
        final String username = JsonPath.read(jwt.getClaims(), issuerProperties.getUsernameJsonPath());
        final String uuid = jwt.getSubject();
        final var userPrincipal = new UserPrincipal(uuid, username, authorities);
        return new AuthorizationTokenUser(userPrincipal, jwt);
    }
}
