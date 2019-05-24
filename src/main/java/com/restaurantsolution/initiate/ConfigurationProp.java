package com.restaurantsolution.initiate;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages= {"com.restaurantsolution.*"})
@EntityScan(basePackages = "com.restaurantsolution.domain")
@EnableTransactionManagement

@EnableJpaRepositories(basePackages= {"com.restaurantsolution.repository"})
//@EnableScheduling
public class ConfigurationProp {
	
	
//	
//	@Bean
//	public ViewResolver getViewResolver() {
//	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//	resolver.setPrefix("/WEB-INF/jsp/");
//	resolver.setSuffix(".jsp");
//	resolver.setViewClass(JstlView.class);
//		
//		
//		return resolver;
//	}

}
