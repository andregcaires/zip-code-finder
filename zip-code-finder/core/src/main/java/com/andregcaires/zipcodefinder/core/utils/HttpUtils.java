package com.andregcaires.zipcodefinder.core.utils;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public interface HttpUtils {

	String httpGet(String zipCode) throws IOException, InterruptedException;
}
