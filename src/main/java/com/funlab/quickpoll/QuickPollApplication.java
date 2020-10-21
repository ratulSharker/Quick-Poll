package com.funlab.quickpoll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class QuickPollApplication {

	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
//		rbms.setBasename("message");
		rbms.addBasenames("message");
		rbms.addBasenames("application");
		rbms.setUseCodeAsDefaultMessage(true);
		rbms.setDefaultEncoding("UTF-8");
		return rbms;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(QuickPollApplication.class, args);
	}

}
