package com.funlab.quickpoll.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket v1ApiConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Version 1").apiInfo(apiInfo("v1")).select()
				.apis(RequestHandlerSelectors.any()).paths((String input) -> {
					return input.startsWith("/error") == false && input.startsWith("/v1/");
				}).build();
	}

	@Bean
	public Docket v2ApiConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Version 2").apiInfo(apiInfo("v2")).select()
				.apis(RequestHandlerSelectors.any()).paths((String input) -> {
					return input.startsWith("/error") == false && input.startsWith("/v2/");
				}).build();
	}

	private ApiInfo apiInfo(String version) {
		return new ApiInfoBuilder().title("QuickPoll REST API")
				.description("QuickPoll Api for creating and managing polls")
				.termsOfServiceUrl("https://www.google.com").license("MIT License")
				.contact(new Contact("Ratul", "https://www.ratul.me", "sharker.ratul.08@gmail.com"))
				.licenseUrl("http://opensource.org/licenses/MIT").version(version).build();
	}

}
