package com.brodygaudel.gestioncomptes.common.xe.services;

import java.io.IOException;
import java.net.URISyntaxException;

import com.brodygaudel.gestioncomptes.common.xe.exceptions.XecdApiException;

public interface XecdHttpClient {
	
	String getResponse(String url, String username, String password) throws URISyntaxException, IOException, XecdApiException;

}
