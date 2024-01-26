package com.anafernandes.catalog.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
//@SecurityScheme(scheme = "custom", type = SecuritySchemeType.OAUTH2, in = SecuritySchemeIn.HEADER, name = "access_token",
//        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
//                authorizationUrl = "http://localhost:9090/realms/CatalogKeycloak",
//                tokenUrl = "http://localhost:9090/realms/CatalogKeycloak/protocol/openid-connect/token", scopes = {})))

public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Info information = new Info()
                .title("Book Catalog API")
                .version("1.0");

        return new OpenAPI().info(information).servers(List.of(server));
    }
}