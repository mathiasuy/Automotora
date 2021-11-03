package com.mathiasuy.automotora.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mathiasuy.automotora.dataaccess.IAutoDataAccess;

@org.springframework.stereotype.Component
public class MapperResolver {

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(MapperResolver.class);

	@Autowired
	@SuppressWarnings("unused")
	private IAutoDataAccess vehiculoDAO;



}
