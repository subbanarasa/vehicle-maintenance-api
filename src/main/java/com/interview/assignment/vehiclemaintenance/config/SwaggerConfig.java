package com.interview.assignment.vehiclemaintenance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket hostApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.interview.assignment.vehiclemaintenance.resource"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Vehicle Maintenance Service")
                .description("Vehicle Maintenance Service Interview Assignment")
                .version("v1.0")
                .termsOfServiceUrl("Terms of service")
                .contact(new Contact("Subbanarasa", "", "subbanarasareddy@yahoo.com"))
                .license("License of API")
                .licenseUrl("API license URL").build();
    }

}
