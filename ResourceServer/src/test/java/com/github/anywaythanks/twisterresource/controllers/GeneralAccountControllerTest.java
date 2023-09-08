package com.github.anywaythanks.twisterresource.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.anywaythanks.twisterresource.converters.SpringAddonsJwtAuthenticationUserConverter;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountPartialResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class GeneralAccountControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    SpringAddonsJwtAuthenticationUserConverter converter;
    Jwt jwt;
    ObjectMapper jacksonMapper;


    void initJwt() {
        jwt = Jwt.withTokenValue("token").header("alg", "none")
                .claim(JwtClaimNames.SUB, "2ffbde6d-29f4-422f-b09f-f27c2f02c720")
                .claim("preferred_username", "user")
                .claim("roles", List.of("ADMIN"))
                .issuer("https://test-issuer/auth/realms/Test")
                .build();
    }

    void initMapper() {
        jacksonMapper = new ObjectMapper();
    }

    @BeforeEach
    void init() {
        initJwt();
        initMapper();
    }

    @Test
    void postRegister() throws Exception {
        AbstractAuthenticationToken token = converter.convert(jwt);
        GeneralAccountCreateRequestDto requestDto = new GeneralAccountCreateRequestDto("test");
        GeneralAccountPartialResponseDto resultDto = new GeneralAccountPartialResponseDto("test", "id1");
        this.mockMvc.perform(post("/api/general").with(authentication(token)).contentType(APPLICATION_JSON)
                        .content(jacksonMapper.writeValueAsString(requestDto)))
                .andExpect(content().string(is(jacksonMapper.writeValueAsString(resultDto))));
    }
}