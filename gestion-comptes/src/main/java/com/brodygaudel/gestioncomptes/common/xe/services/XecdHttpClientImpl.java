package com.brodygaudel.gestioncomptes.common.xe.services;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import com.brodygaudel.gestioncomptes.common.xe.exceptions.ErrorResponse;
import com.brodygaudel.gestioncomptes.common.xe.exceptions.XecdApiException;
import com.brodygaudel.gestioncomptes.common.xe.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import javax.net.ssl.SSLContext;

@Service
@Slf4j
public class XecdHttpClientImpl implements XecdHttpClient {

	private HttpClient client = null;


	public XecdHttpClientImpl() {
		Integer connectTimeout = 3600;
		HttpClientBuilder builder = HttpClientBuilder.create();
		if (connectTimeout != null) {
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).build();
			builder.setDefaultRequestConfig(requestConfig);
		}
		try {
			SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
			sslContext.init(null,null,null);
			builder.setSSLContext(sslContext);
			client = builder.build();
		} catch(Exception e) {
			log.error("Could not establish SSL Context properly for HTTPClient.");
			client = null;
		}
	}

	@Override
	public String getResponse(String url, String username, String password) throws URISyntaxException, IOException, XecdApiException {
		JsonUtils jsonUtils = new JsonUtils();
		HttpGet get = new HttpGet(new URI(url));
		Base64 base64 = new Base64();
		String encodedUsernamePassword = base64.encodeAsString((username + ":" + password).getBytes());

		get.setHeader("Authorization", "Basic " + encodedUsernamePassword);

		if(log.isDebugEnabled()) {
			log.debug("request = {}", new Gson().toJson(get));
		}

		HttpResponse response = client.execute(get);

		HttpEntity bodyEntity = response.getEntity();

		String responseString = EntityUtils.toString(bodyEntity);

		log.debug("response = {}", responseString);

		if (response.getStatusLine().getStatusCode() != 200) {
			ErrorResponse errorResponse = jsonUtils.fromJson(responseString, ErrorResponse.class);
			throw new XecdApiException("Error received from XECD with status = " + response.getStatusLine(), errorResponse, response.getStatusLine().getStatusCode());
		}

		return responseString;
	}

}
