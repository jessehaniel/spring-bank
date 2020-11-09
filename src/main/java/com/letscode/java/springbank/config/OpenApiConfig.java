package com.letscode.java.springbank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!producao")
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI(@Value("${build.version}") String appVersion,
        @Value("${build.title}") String title) {
        return new OpenAPI().info(new Info()
            .title(title)
            .version(appVersion)
            .description("Spring Bank Investimentos"));
    }
    
}
