package com.github.anywaythanks.twisterresource.config;

import com.github.anywaythanks.twisterresource.configs.AuthorizeServerProperties;
import com.github.anywaythanks.twisterresource.configs.HibernateServerProperties;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootConfiguration
@ComponentScan
public class SpringBootTestConfig {
}
