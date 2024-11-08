package com.upiiz.relaciones_equipo.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info=@Info(
                title="API de Torneos",
                description="Esta api proporciona accersoa los recurisos de los torneos 2024-2029",
                version = "1.0.0",
                contact = @Contact(
                        name="Sebastian Colin",
                        email = "jcolinb1800@alumno.ipn.mx",
                        url = "http://localhost:8080/contacto"
                ),
                license = @License(),
                termsOfService = ""
        ),
        servers ={
                @Server(
                        description = "Servidor de puebas",
                        url = "http://pruebas.com:8080/api/v1"),
                @Server(
                        description = "Servidor de producción",
                        url = "http://localhost:8080/api/v1")
        },
        tags = {
                @Tag(
                        name = "Competencias",
                        description = "Endpoints para los recursos de competencias"
                ),
                @Tag(
                        name = "Entrenadores",
                        description = "Endpoints para entrenadores"

                ),
                @Tag(
                        name = "Equipos",
                        description = "Endpoints para equipos"

                ),
                @Tag(
                        name = "Jugadores",
                        description = "Endpoints para jugadores"

                ),
                @Tag(
                        name = "Ligas",
                        description = "Endpoints para las ligas"

                )
        }


)
public class OpenApiConfiguration {
}
