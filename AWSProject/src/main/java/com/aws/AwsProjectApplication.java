package com.aws;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class AwsProjectApplication extends SpringBootServletInitializer
						implements EmbeddedServletContainerCustomizer{

	public static void main(String[] args) {
		SpringApplication.run(AwsProjectApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		Map<String, Object> defaultProperties = new HashMap<String, Object>();
		defaultProperties.put("spring.config.location", "src/main/resoruces/application.properties");
		
		return application.sources(AwsProjectApplication.class).properties(defaultProperties);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		// TODO Auto-generated method stub
		container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/accesdenied"));
	}

}
