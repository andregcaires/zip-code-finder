package com.andregcaires.zipcodefinder.core.services;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public interface ZipCodeFinderService {

	HttpResponse<String> httpGet(String url) throws IOException, InterruptedException;
}
