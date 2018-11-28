package com.ebookstore.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.ebookstore.app.EBSRestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:swagger.properties")
@ComponentScan(basePackageClasses = EBSRestController.class)
@Configuration
@EnableAutoConfiguration
public class SwaggerConfig {
	private static final String SWAGGER_API_VERSION = "1.0";
	private static final String LICENSE_TEXT = "License";
	private static final String title = "EBookStore REST API";
	private static final String description = "RESTful API for EBookStore";

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title).version(SWAGGER_API_VERSION).description(description)
				.license(LICENSE_TEXT).build();
	}

	@Bean
	public Docket ebookstoreDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).pathMapping("/").select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
}
