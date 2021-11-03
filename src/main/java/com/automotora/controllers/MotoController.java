package com.automotora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automotora.requests.MotoRequest;
import com.automotora.responses.MotoResponse;
import com.automotora.service.MotoServices;

@RestController
@RequestMapping("/moto")
public class MotoController extends Controller<MotoResponse, MotoRequest, Long>{

    public MotoController(@Autowired MotoServices services) {
    	super.controller = services;
	}

}
