package com.mathiasuy.automotora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathiasuy.automotora.requests.MotoRequest;
import com.mathiasuy.automotora.responses.MotoResponse;
import com.mathiasuy.automotora.service.MotoServices;

@RestController
@RequestMapping("${controller.moto}")
public class MotoController extends Controller<MotoResponse, MotoRequest, Long>{

    public MotoController(@Autowired MotoServices services) {
    	super.controller = services;
	}

}
