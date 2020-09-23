package xyz.idiosoul.fair.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.OAuth2Scheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

/**
 * @author vincent
 */
@Configuration
public class SpringFoxConfig {
    private static final String CLIENT_ID = "messaging-client";
    private static final String CLIENT_SECRET = "secret";
    private static final String AUTH_SERVER = " http://auth-server:9000/oauth2";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(singletonList(securityContext()));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/api/v1.0/.*"))
                .build();
    }

    @Bean
    SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        return Arrays.asList(
                OAuth2Scheme.OAUTH2_AUTHORIZATION_CODE_FLOW_BUILDER
                        .name("spring_oauth")
                        .authorizationUrl(AUTH_SERVER + "/authorize")
                        .tokenUrl(AUTH_SERVER + "/token")
                        .scopes(Arrays.asList(
                                new AuthorizationScope("message.read", "Write scope"),
                                new AuthorizationScope("message.write", "Read scope")))
                        .build());
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("message.read", "Write scope"),
                new AuthorizationScope("message.write", "Read scope")
        };
        return scopes;
    }

}
