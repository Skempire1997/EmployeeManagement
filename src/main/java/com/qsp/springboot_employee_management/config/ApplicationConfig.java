package com.qsp.springboot_employee_management.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
public class ApplicationConfig {
	@Bean
	public Docket getDocket() {
		Contact contact=new Contact("Qspiders", "Qspiders.com", "qspiders@gmail.com");
		List<VendorExtension>extensions=new ArrayList<VendorExtension>();
		ApiInfo apiInfo=new ApiInfo("Employee_Management", "To manage Employee data.", "Version 1.0", "wwe.ems.com", contact, "IND098", "www.ems.com", extensions);
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.qsp.springboot_employee_management")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
	}
	
//	url of Swagger
//	http://localhost:8080/swagger-ui.html#

}
