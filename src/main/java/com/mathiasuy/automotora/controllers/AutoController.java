package com.automotora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automotora.requests.AutoRequest;
import com.automotora.responses.AutoResponse;
import com.automotora.service.AutoServices;

@RestController
@RequestMapping("/auto")
public class AutoController extends Controller<AutoResponse, AutoRequest, Long>{

    public AutoController(@Autowired AutoServices services) {
    	super.controller = services;
	}

}
