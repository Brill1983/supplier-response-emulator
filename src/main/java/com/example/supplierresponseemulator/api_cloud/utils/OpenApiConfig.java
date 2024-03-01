package com.example.supplierresponseemulator.api_cloud.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "ApiCloud Emulator Api",
                description = "Emulation od supplers api", version = "1.0.0",
                contact = @Contact(
                        name = "Babaev Dmitry",
                        email = "brill@yandex.ru"
                )
        )
)
public class OpenApiConfig {
}
