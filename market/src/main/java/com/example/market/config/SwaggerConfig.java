package com.example.market.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//                .addOpenApiCustomiser(openApi -> openApi
 //               .addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
                .build();
    }

}

