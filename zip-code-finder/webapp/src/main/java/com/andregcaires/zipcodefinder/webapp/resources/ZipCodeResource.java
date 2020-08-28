package com.andregcaires.zipcodefinder.webapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andregcaires.zipcodefinder.core.services.ZipCodeFinderService;

@RestController
@RequestMapping(value = {"/zipcode"})
public class ZipCodeResource {

	@Autowired
	private ZipCodeFinderService zipCodeFinderService;
	
	@RequestMapping(value = {"/{zipCode}/viacep"}, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> findByZipCode(@PathVariable String zipCode) {
		
		var body = zipCodeFinderService.findAddressByZipCode(zipCode);
		
		if (body.getError() != null) {
			
			return ResponseEntity.badRequest().body(body.getError());
		}
		
		return ResponseEntity.ok().body(body.getAddress());
	}
}
