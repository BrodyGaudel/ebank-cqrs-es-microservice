package com.brodygaudel.gestioncomptes.common.xe.restcontroller;

import com.brodygaudel.gestioncomptes.common.xe.exceptions.XecdApiException;
import com.brodygaudel.gestioncomptes.common.xe.model.ConvertFromResponse;
import com.brodygaudel.gestioncomptes.common.xe.services.XecdApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v3/xe")
public class XeRestController {
	
	private static final String TND = "TND";
	private static final String XAF = "XAF";
	private final XecdApiService xecdApiService;

	public XeRestController(XecdApiService xecdApiService) {
		
		this.xecdApiService = xecdApiService;
	}
	
	@GetMapping("/convert")
	@ResponseBody
	public ConvertFromResponse convertion() throws XecdApiException {
		String accountId = "xe account id ";
		String apiKey = "xe api key";
		
		return xecdApiService.convertFrom(
				accountId, apiKey, TND, XAF, (double) 5000, false);
	}
	
	@GetMapping("/convert/{from}/{to}/{amount}")
	public ConvertFromResponse convert(@PathVariable(name="from") String from, @PathVariable(name="to") String to, @PathVariable(name="amount") double amount) throws XecdApiException {
		String accountId = "gaudelsoftware174226874";
		String apiKey = "4t5qqc23fdh6hea8g7bhlc2gba";
		
		return xecdApiService.convertFrom(
				accountId, apiKey, from, to, amount, false);
	}
	

}
