package com.mathiasuy.automotora.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mathiasuy.automotora.service.CrudServices;

public abstract class Controller<Response, Request, Id> {

    protected CrudServices<Response, Request, Id> controller;

    @PostMapping("")
	@CrossOrigin(origins = "${crossOrigin}")
	@ResponseStatus(code = HttpStatus.CREATED)
    public Response add(@RequestBody Request request){
        return controller.add(request);
    }

    @PutMapping("")
	@CrossOrigin(origins = "${crossOrigin}")
    public void update(Id id, @RequestBody Request request) {
        controller.update(id,request);
    }

    @DeleteMapping("")
	@CrossOrigin(origins = "${crossOrigin}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(Id id) {
        controller.delete(id);
    }

    @GetMapping("{id}")
	@CrossOrigin(origins = "${crossOrigin}")
    public Response findById(@PathVariable Id id) {
        return controller.findById(id);
    }

    @GetMapping("")
	@CrossOrigin(origins = "${crossOrigin}")
    public List<Response> findAll() {
        return controller.findAll();
    }

}
