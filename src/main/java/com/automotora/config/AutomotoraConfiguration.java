package com.automotora.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.automotora.filters.Filter;

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
public class AutomotoraConfiguration extends WebMvcConfigurerAdapter {

	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.automotora.controllers"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("API Automotora", "API Automotora", "1.0",
				"http://mathias.uy/",
				new Contact("Mathias Battistella",
						"mathias.uy",
						"mathias2b@gmail.com"),
				"LICENSE", "LICENSE URL", Collections.emptyList());
	}

    @Bean
    public AuditorAware<String> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
         */
        return () -> Optional.ofNullable("ANONYMOUS");
    }
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }


    @Bean //Para que el atributo del contexto funcione!!!!
    public Filter shallowEtagHeaderFilter() {
        return new Filter();
    }

}