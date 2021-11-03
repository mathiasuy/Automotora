package com.automotora.utils;

import java.util.ArrayList;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.automotora.model.Auto;
import com.automotora.model.Moto;
import com.automotora.requests.AutoRequest;
import com.automotora.requests.MotoRequest;
import com.automotora.responses.AutoResponse;
import com.automotora.responses.MotoResponse;


@Mapper(componentModel = "spring", uses = MapperResolver.class, imports = {ArrayList.class})
public interface IMapper {

	AutoResponse toResponse(Auto model);	
	MotoResponse toResponse(Moto request);

	@Mapping(ignore = true, target = "correlationId")
	@Mapping(ignore = true, target = "created")
	@Mapping(ignore = true, target = "createdBy")
	@Mapping(ignore = true, target = "id")
	@Mapping(ignore = true, target = "lastUpdate")
	Auto fromRequest(AutoRequest request);	

	@Mapping(ignore = true, target = "correlationId")
	@Mapping(ignore = true, target = "created")
	@Mapping(ignore = true, target = "createdBy")
	@Mapping(ignore = true, target = "id")
	@Mapping(ignore = true, target = "lastUpdate")
	Moto fromRequest(MotoRequest request);
		
}

