package com.mathiasuy.automotora.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.mathiasuy.automotora.filters.Filter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2	
@EnableAutoConfiguration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AutomotoraConfiguration {

	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.mathiasuy.automotora.controllers"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("API Automotoras", "API Automotoras", "1.0",
				"http://mathias.uy/",
				new Contact("Mathias Battistella",
						"mathias.uy",
						"mathias2b@gmail.com"),
				"LICENSE", "LICENSE URL", Collections.emptyList());
	}

    @Bean
    public AuditorAware<String> auditorProvider() {
    	//Usuario en curso, para spring security usar SecurityContextHolder.getContext().getAuthentication().getName()
        return () -> Optional.ofNullable("ANONYMOUS");
    }
    
    @Bean 
    public Filter shallowEtagHeaderFilter() {
    	//Para que el atributo del contexto funcione!!!!
        return new Filter();
    }

}