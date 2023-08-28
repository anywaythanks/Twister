package com.github.anywaythanks.twisterresource.configs;

import com.github.anywaythanks.twisterresource.converters.SpringAddonsJwtAuthenticationUserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {
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
}
