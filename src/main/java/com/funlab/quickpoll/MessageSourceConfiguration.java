package com.funlab.quickpoll;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {

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
}
