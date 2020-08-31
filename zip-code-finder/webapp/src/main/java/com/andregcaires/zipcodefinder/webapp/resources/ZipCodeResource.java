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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = {"/zipcode"})
public class ZipCodeResource {

	@Autowired
	@Qualifier("viaCep")
	private ZipCodeFinderService zipCodeFinderServiceByViaCep;
	
	@Autowired
	@Qualifier("database")
	private ZipCodeFinderService zipCodeFinderServiceByDatabase;
	
	@ApiOperation(value = "Busca um endereço através do CEP no web service Via Cep")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um endereço quando encontrado"),
	    @ApiResponse(code = 400, message = "CEP inválido"),
	})
	@GetMapping(value = {"/{zipCode}/viacep"}, produces = "application/json")
	public ResponseEntity<String> findByZipCode(@PathVariable String zipCode) throws JsonProcessingException {
		
		var body = zipCodeFinderServiceByViaCep.findAddressByZipCode(zipCode);
		
		if (body.getError() != null) {
			
			return ResponseEntity.badRequest().body(body.getError().toJson());
		}
		
		return ResponseEntity.ok().body(body.getAddress().toJson());
	}
	
	@ApiOperation(value = "Busca um endereço através do CEP no banco de dados")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna um endereço quando encontrado"),
	    @ApiResponse(code = 400, message = "CEP inválido"),
	})
	@GetMapping(value = {"/{zipCode}/database"}, produces = "application/json")
	public ResponseEntity<String> findByDatabase(@PathVariable String zipCode) throws JsonProcessingException {
		
		var body = zipCodeFinderServiceByDatabase.findAddressByZipCode(zipCode);
		
		if (body.getError() != null) {
			
			return ResponseEntity.badRequest().body(body.getError().toJson());
		}
		
		return ResponseEntity.ok().body(body.getAddress().toJson());
	}
}
