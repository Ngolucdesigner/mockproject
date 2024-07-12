package com.anks.tech.ecommerce.Config;


import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
//@EnableSwagger2

public class SwaggerConfiguaration {


    private Contact contact;


    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.description}") String description,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.license}") String license,
                           @Value("${open.api.license.url}") String licenseUrl,
                           @Value("${open.api.contact.url}") String contactUrl,
                           @Value("${open.api.contact.name}") String contactName,
                           @Value("${open.api.contact.email}") String contactEmail,
                           @Value("${open.api.servers.url}") String serverUrl,
                           @Value("${open.api.servers.description}") String serverDescription
    ) {
        return new OpenAPI().info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(new License().name(license).url(licenseUrl))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name(contactName)
                                .email(contactEmail)
                                .url(contactUrl))
                )
                .servers(List.of(new Server().url(serverUrl).description(serverDescription)))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .security(List.of( new SecurityRequirement().addList("bearerAuth")));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("API_GROUP1")
                .packagesToScan("com.anks.tech.ecommerce.Controller")
                .build();
    }

}
