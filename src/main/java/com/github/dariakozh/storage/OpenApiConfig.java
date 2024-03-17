package com.github.dariakozh.storage;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Storage Api",
                description = "Storage", version = "1.0.0",
                contact = @Contact(
                        name = "Kozhanova Daria",
                        email = "5115777@mail.ru")
        )
)
public class OpenApiConfig {
}
