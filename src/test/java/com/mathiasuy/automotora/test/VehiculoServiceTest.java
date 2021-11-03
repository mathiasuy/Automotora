package com.mathiasuy.automotora.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mathiasuy.automotora.utils.IMapperImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathiasuy.automotora.dataaccess.IAutoDataAccess;
import com.mathiasuy.automotora.responses.AutoResponse;
import com.mathiasuy.automotora.service.AutoServices;
import com.mathiasuy.automotora.utils.IMapper;
import com.mathiasuy.automotora.utils.MapperResolver;

//Para probar capa controladores
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = AutoController.class)

//Para probar capa de jpa
//@DataJpaTest

//Para probar todo (lo ideal sería probar capas por separado pero se junta todo a modo de demo)
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({AutoServices.class, IMapperImpl.class, MapperResolver.class})
@Transactional(propagation = Propagation.REQUIRES_NEW)
@AutoConfigureMockMvc
public class VehiculoServiceTest {
	
    @Autowired(required=true)
    private IAutoDataAccess autoDataAccess;
    
    @Autowired(required=true)
    private AutoServices autoServices;
    
    @Value("${controller.moto}")
    private String controllerMotoPath;    
    
    @Autowired
    private MockMvc mockMvc;    

    @Autowired
    private ObjectMapper objectMapper;    
    
    @Autowired
    private IMapper iMapper;
    
    @Before
    public void before() throws JsonProcessingException, Exception {
    	mockMvc.perform(post(controllerMotoPath)
    			.contentType("application/json")
    			.content(objectMapper.writeValueAsString(FactoryObjects.generateAutoRequest1())))
    	.andExpect(status().isCreated());    	
    	this.autoDataAccess.saveAndFlush(FactoryObjects.generateAuto1());
    }    
    
    @After
    public void clear() {
        this.autoDataAccess.deleteAll();
    }    
    
    @Test
    public void test() {
    	List<AutoResponse> autos = autoServices.findAll();
    	AutoResponse autoResponse = iMapper.toResponse(FactoryObjects.generateAuto1());
    	autoResponse.setId(Long.valueOf(1));
    	assertEquals(autos.get(0), autoResponse); 
    }
    
}
