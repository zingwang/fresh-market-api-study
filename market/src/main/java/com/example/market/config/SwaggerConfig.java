package com.example.market.config;//package com.example.market.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile(value = "!prod")
@OpenAPIDefinition(
        info = @Info(title = "Demo API",
                description = "Demo API Description",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/v1/**"};

        return GroupedOpenApi.builder()
                .group("API v1")
                .pathsToMatch(paths)
                .addOpenApiCustomizer(openApi -> openApi.components(
                        new Components().addSecuritySchemes("Authorization", securityScheme)))
                .build();
    }

    SecurityScheme securityScheme = new io.swagger.v3.oas.models.security.SecurityScheme()
            .name("Authorization")
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER);


}
