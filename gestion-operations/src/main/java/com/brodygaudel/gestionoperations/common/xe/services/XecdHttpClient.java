package com.brodygaudel.gestionoperations.common.xe.services;

import com.brodygaudel.gestionoperations.common.xe.exceptions.XecdApiException;

import java.io.IOException;
import java.net.URISyntaxException;


public interface XecdHttpClient {
	
	String getResponse(String url, String username, String password) throws URISyntaxException, IOException, XecdApiException;

}
