package com.mathiasuy.automotora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mathiasuy.automotora.utils.IMapper;

public abstract class CrudServices<Response, Request, Id> {
		
	@Autowired
	protected IMapper iMapper;
	
	public abstract Response add(Request request);
	public abstract Response update(Id id, Request request);
	public abstract void delete(Id id);
	public abstract Response findById(Id id);
	public abstract List<Response> findAll();
	
    
}