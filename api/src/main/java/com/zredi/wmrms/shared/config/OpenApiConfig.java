package com.zredi.wmrms.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class OpenApiConfig {
  
  
  @Bean
   OpenAPI customizeOpenAPI() {
      final String securitySchemeName = "bearerAuth";
      return new OpenAPI()
        .addSecurityItem(new SecurityRequirement()
          .addList(securitySchemeName))
        .components(new Components()
          .addSecuritySchemes(securitySchemeName, new io.swagger.v3.oas.models.security.SecurityScheme()
            .name(securitySchemeName)
            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")));
      }

}
