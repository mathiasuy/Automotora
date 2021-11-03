package com.mathiasuy.automotora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mathiasuy.automotora.requests.AutoRequest;
import com.mathiasuy.automotora.responses.AutoResponse;
import com.mathiasuy.automotora.service.AutoServices;

@RestController
@RequestMapping("${controller.auto}")
public class AutoController extends Controller<AutoResponse, AutoRequest, Long>{

    public AutoController(@Autowired AutoServices services) {
    	super.controller = services;
	}

}
