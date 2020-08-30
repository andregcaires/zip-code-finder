package com.andregcaires.zipcodefinder.webapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.zipcodefinder.core.services.ZipCodeFinderService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value = {"/zipcode"})
public class ZipCodeResource {

	@Autowired
	@Qualifier("viaCep")
	private ZipCodeFinderService zipCodeFinderServiceByViaCep;
	
	@Autowired
	@Qualifier("database")
	private ZipCodeFinderService zipCodeFinderServiceByDatabase;
	
	@GetMapping(value = {"/{zipCode}/viacep"}, produces = "application/json")
	public ResponseEntity<String> findByZipCode(@PathVariable String zipCode) throws JsonProcessingException {
		
		var body = zipCodeFinderServiceByViaCep.findAddressByZipCode(zipCode);
		
		if (body.getError() != null) {
			
			return ResponseEntity.badRequest().body(body.getError().toJson());
		}
		
		return ResponseEntity.ok().body(body.getAddress().toJson());
	}
	
	@GetMapping(value = {"/{zipCode}/database"}, produces = "application/json")
	public ResponseEntity<String> findByDatabase(@PathVariable String zipCode) throws JsonProcessingException {
		
		var body = zipCodeFinderServiceByViaCep.findAddressByZipCode(zipCode);
		
		if (body.getError() != null) {
			
			return ResponseEntity.badRequest().body(body.getError().toJson());
		}
		
		return ResponseEntity.ok().body(body.getAddress().toJson());
	}
}
