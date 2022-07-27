package service.ricotunes.giftcards.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*created by Hector Developers
6-30-2022
*/
@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("https://localhost:8080/api/v1/swagger-ui.html"))
                .paths(PathSelectors.any())
                .build();
    }
}