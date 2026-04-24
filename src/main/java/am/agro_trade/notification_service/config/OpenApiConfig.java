package am.agro_trade.notification_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI notificationServiceOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Notification Service API")
                        .description("OpenAPI documentation for notification delivery and notification settings management")
                        .version("v1")
                        .contact(new Contact()
                                .name("Agro Trade"))
                        .license(new License()
                                .name("Internal Use")));
    }
}
