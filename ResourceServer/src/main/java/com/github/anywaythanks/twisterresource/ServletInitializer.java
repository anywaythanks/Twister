package com.github.anywaythanks.twisterresource;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TwisterResourceApplication.class);
    }

}
