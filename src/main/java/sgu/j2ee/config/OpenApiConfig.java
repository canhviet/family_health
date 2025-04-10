package sgu.j2ee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI familyHealthOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Family Health API")
                        .description("API for Family Health Management System")
                        .version("v1.0"));
    }
}