package xyz.idiosoul.fair.order.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author vincent
 */
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(singletonList(apiKey()))
                .securityContexts(singletonList(securityContext()));
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/v1.0/.*"))
                .build();
    }
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference(HttpHeaders.AUTHORIZATION, authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey(HttpHeaders.AUTHORIZATION,"apy_key" , In.HEADER.name());
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                null, null, null,
                null, // app name
                "BEARER ", // api key value
                ApiKeyVehicle.HEADER, "Authorization", ",");
    }

}
