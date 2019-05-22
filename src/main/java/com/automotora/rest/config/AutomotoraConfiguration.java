package com.automotora.rest.config;

import com.automotora.service.controllers.IVehiculoController;
import com.automotora.service.controllers.impl.VehiculoController;
import com.automotora.service.dataaccess.IVehiculoDAO;
import com.automotora.service.dataaccess.impl.VehiculoDAO;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.automotora.service.*","com.automotora.service.dataaccess.impl"})
//@Import({RestSBCommonsConfiguration.class})
public class AutomotoraConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

}