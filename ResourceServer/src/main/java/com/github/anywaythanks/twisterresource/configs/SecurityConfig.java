package com.github.anywaythanks.twisterresource.configs;

import com.github.anywaythanks.twisterresource.converters.JwtGrantedAuthoritiesConverter;
import com.github.anywaythanks.twisterresource.converters.SpringAddonsJwtAuthenticationUserConverter;
import com.jayway.jsonpath.JsonPath;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
//        http.apply(authorizationServerConfigurer);
//        authorizationServerConfigurer.tokenGenerator()
//        return http.build();
//    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorizeHttpRequests) ->
//                        authorizeHttpRequests
//                                .requestMatchers("/log").authenticated()
//                                .anyRequest().permitAll())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .oauth2ResourceServer(resourceServerConfigurer -> resourceServerConfigurer
//                        .jwt(Customizer.withDefaults()));
//        //.formLogin(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    SecurityFilterChain resourceServerSecurityFilterChain(
            HttpSecurity http,
            @Value("${origins:[]}") String[] origins,
            SpringAddonsJwtAuthenticationUserConverter authenticationConverter,
            HandlerMappingIntrospector mvcHandlerMappingIntrospector)
            throws Exception {
        Logger logger = LoggerFactory.getLogger("filter");
        http.oauth2ResourceServer(resourceServer ->
                resourceServer.jwt(jwtConfigurer ->
                        jwtConfigurer.jwtAuthenticationConverter(authenticationConverter)));
        http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(handeling -> handeling.authenticationEntryPoint((request, response, authException) -> {
            logger.info(handeling.toString());
            response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"Restricted Content\"");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }));
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/public/**"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "resources/**"))
                        .permitAll()
                        .requestMatchers(
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/item"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/money/type"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/case"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/item/**"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/money/type/**"),
                                new MvcRequestMatcher(mvcHandlerMappingIntrospector, "api/case/**"))
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated());
        http.cors(cors -> {
            if (origins.length == 0) {
                cors.disable();
            } else {
                cors.configurationSource(corsConfig((List.of(origins))));
            }
        });
        return http.build();
    }

    CorsConfigurationSource corsConfig(List<String> allowedOrigins) {
        final var source = new UrlBasedCorsConfigurationSource();

        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    //    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        KeyPair keyPair = generateRsaKey();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        JWK rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}
