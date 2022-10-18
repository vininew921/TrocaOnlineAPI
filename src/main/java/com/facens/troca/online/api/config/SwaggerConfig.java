package com.facens.troca.online.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securitySchemes(List.of(apiKey()))
                .securityContexts(List.of(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.facens.troca.online.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Troca Online")
                .description("Documentação da API Troca Online")
                .version("1.0.0-SNAPSHOT")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope adminScope = new AuthorizationScope("ADMIN", "Admin access");
        AuthorizationScope userScope = new AuthorizationScope("USER", "User access");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = adminScope;
        authorizationScopes[1] = userScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }
}
