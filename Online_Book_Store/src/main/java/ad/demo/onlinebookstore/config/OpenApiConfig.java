package ad.demo.onlinebookstore.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Online Book Store REST API",
                version = "0.0.1-SNAPSHOT"
        )
)
public class OpenApiConfig {
}
