package org.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =  {"org.transport", "org.application", "org.domain", "org.infrastructure"})
public class InfrastructureLayerTests {
    @Test
    void contextLoads() {
    }
}
