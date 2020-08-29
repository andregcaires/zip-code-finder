package com.andregcaires.zipcodefinder.webapp.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.andregcaires.zipcodefinder.core.dtos.AddressDto;
import com.andregcaires.zipcodefinder.core.dtos.ErrorDto;
import com.andregcaires.zipcodefinder.core.dtos.ResultDto;
import com.andregcaires.zipcodefinder.core.services.ZipCodeFinderService;

@WebMvcTest(ZipCodeResource.class)
@AutoConfigureMockMvc
class ZipCodeResourceTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ZipCodeFinderService zipCodeService;
    
    public AddressDto mockAddress() {
    	
    	AddressDto address = new AddressDto();
    	address.setCity("Cidade mock");
    	address.setNeighborhood("Bairro mockado");
    	address.setState("MOCK");
    	address.setStreet("Rua dos Mocks");
    	
    	return address;
    }
    
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void mustFindValidAddress() throws Exception {
    	
    	AddressDto address = mockAddress();    	
    	ResultDto resultDto = new ResultDto();
    	resultDto.setAddress(address);
    	
    	given(zipCodeService.findAddressByZipCode("12345678")).willReturn(resultDto);
    	
        mvc.perform(get("/zipcode/12345678/viacep")
        	      .contentType(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void mustReturnErrorMessageFromService() throws Exception {
    	
    	ErrorDto errorDto = new ErrorDto("CEP inválido");   	
    	ResultDto resultDto = new ResultDto();
    	resultDto.setError(errorDto);
    	
    	given(zipCodeService.findAddressByZipCode("ABCDEFGH")).willReturn(resultDto);
    	
        mvc.perform(get("/zipcode/ABCDEFGH/viacep")
        	      .contentType(MediaType.APPLICATION_JSON))
        	      .andExpect(status().isBadRequest());
    }
    
}
