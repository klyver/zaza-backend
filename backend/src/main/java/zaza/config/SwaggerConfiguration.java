package zaza.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket sessionsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sessions")
                .apiInfo(new ApiInfoBuilder()
                        .title("API for handling sessions")
                        .version("1.0")
                        .build())
                .select()
                .paths(sessionPaths())
                .build()
                .enableUrlTemplating(true);
    }

    @Bean
    public Docket catalogApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("supplier-api")
                .apiInfo(new ApiInfoBuilder()
                        .title("API for suppliers")
                        .version("1.0")
                        .build())
                .select()
                .paths(supplierPaths())
                .build()
                .enableUrlTemplating(true);
    }


    private Predicate<String> sessionPaths() {
        return regex("/api/session.*");
    }

    private Predicate<String> supplierPaths() {
        return (input -> input.startsWith("/api/categories") || input.startsWith("/api/products") || input.startsWith("/api/productOptions") || input.startsWith("/api/order"));
    }

}
