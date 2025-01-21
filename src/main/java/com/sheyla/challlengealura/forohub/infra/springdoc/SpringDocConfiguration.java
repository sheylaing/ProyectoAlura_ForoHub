package com.sheyla.challlengealura.forohub.infra.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))

                .info(new Info()
                        .title("Forohub API")
                        .description("Una API para gestionar usuarios, tópicos, respuestas, perfiles y cursos en un foro, con un sistema de autenticación basado en tokens JWT. ")
                        .contact(new Contact()
                                .name("Sheyla Marin Reymondi")
                                .email("shey.contacto.81@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://forohub/api/licencia"))
                )
                ;
    }
}
