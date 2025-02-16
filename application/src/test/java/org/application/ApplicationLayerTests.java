package org.application;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =  {"org.transport", "org.application", "org.domain", "org.infrastructure"})
public class ApplicationLayerTests {
    @Test
    void contextLoads() {
    }
}
