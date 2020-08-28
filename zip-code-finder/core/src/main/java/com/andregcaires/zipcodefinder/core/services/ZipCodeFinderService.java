package com.andregcaires.zipcodefinder.core.services;

import org.springframework.stereotype.Service;

import com.andregcaires.zipcodefinder.core.dtos.ResultDto;

@Service
public interface ZipCodeFinderService {

	ResultDto findAddressByZipCode(String zipCodeString);
}
