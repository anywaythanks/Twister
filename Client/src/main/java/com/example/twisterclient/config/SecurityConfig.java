package com.example.twisterclient.config;

import com.example.twisterclient.services.KeycloakLogoutHandler;
import jakarta.servlet.DispatcherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    private final KeycloakLogoutHandler keycloakLogoutHandler;
    SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
        this.keycloakLogoutHandler = keycloakLogoutHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector mvcHandlerMappingIntrospector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/home"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/cases"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/cases/**"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/WEB-INF/jsp/home.jsp"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/WEB-INF/jsp/cases/cases.jsp"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/WEB-INF/jsp/cases/case.jsp"),
                                        new MvcRequestMatcher(mvcHandlerMappingIntrospector, "/WEB-INF/jsp/includes/**"))
                                .permitAll()
                                .anyRequest().authenticated())

                .oauth2Login(Customizer.withDefaults()).logout(handler ->
                        handler
                                .logoutUrl("/logout")
                                .logoutSuccessHandler(keycloakLogoutHandler)
                                .permitAll());
        //.formLogin(Customizer.withDefaults());

        return http.build();
    }

    //    @Bean
    JwtDecoder jwtDecoder() {
//        NimbusJwtDecoderJwkSupport jwtDecoder = (NimbusJwtDecoderJwkSupport)
//                JwtDecoders.fromOidcIssuerLocation(issuerUri);
//
//        OAuth2TokenValidator<Jwt> audienceValidator = new    AudienceValidator(jHipsterProperties.getSecurity().getOauth2().getAudience());
//        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
//
//        jwtDecoder.setJwtValidator(withAudience);

        //??
        return null;
    }
}
