package com.vrunk.vrunk_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vrunk.vrunk_backend"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.customInfo());
    }

    private ApiInfo customInfo() {

        Contact contact = new Contact("Vrunk Team",
                "https://vrunk.com",
                "support@vrunk.com");

        return new ApiInfo("API REST Documentation", // Title
                "API REST pour l'application Vrunk", // Description
                "1.0", // Version
                "Terms of Service", // Terms of Service
                contact, // Contact
                "Apache Licence Version 2.0", // License
                "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
