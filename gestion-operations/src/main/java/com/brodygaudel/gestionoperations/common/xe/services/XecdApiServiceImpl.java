package com.brodygaudel.gestionoperations.common.xe.services;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.MessageFormat;

import com.brodygaudel.gestionoperations.common.xe.exceptions.XecdApiException;
import com.brodygaudel.gestionoperations.common.xe.model.*;
import com.brodygaudel.gestionoperations.common.xe.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class XecdApiServiceImpl implements XecdApiService {

	private String accountId;
	private String apiKey;
	private String serverPrefix;


	private static final String  ACCOUNT_INFO_URL = "{0}/v1/account_info";
	private static final String CURRENCIES_URL = "{0}/v1/currencies/?";
	private static final String CONVERT_FROM_URL = "{0}/v1/convert_from?to={1}";
	private static final String CONVERT_TO_URL = "{0}/v1/convert_to?from={1}";
	private static final String HISTORIC_RATE_URL = "{0}/v1/historic_rate/?to={1}&date={2}";
	private static final String HISTORIC_RATE_PERIOD_URL = "{0}/v1/historic_rate/period/?to={1}";
	private static final String MONTHLY_AVERAGE_URL = "{0}/v1/monthly_average?to={1}";
	private static final String ERROR_HISTORIC_RATES_FROM_XECD = "Error getting historic rates from XECD API";
	private static final String ERROR_CURRENCIES_FROM_XECD = "Error getting currencies from XECD API";
	private static final String CALLING = "Calling {}";
	private static final String ERROR_RESPONSE_FROM_XECD = "Error getting response from XECD API";
	private static final String FROM = "&from=";
	private static final String OBSOLETE = "&obsolete=";
	private static final String AMOUNT = "&amount=";
	private static final String INVERSE = "&inverse=";

	private JsonUtils jsonUtils;

	private final XecdHttpClient wsClient;

	public XecdApiServiceImpl(XecdHttpClientImpl client) {
		
		this.accountId = "your xe account id";
		this.apiKey = "your xe api key";
		this.serverPrefix = "https://xecdapi.xe.com";
		this.jsonUtils = new JsonUtils();
		this.wsClient = client;
	}

	
	@Override
	public AccountInfoResponse getAccountInfo(String accountId, String apiKey) throws URISyntaxException, IOException, XecdApiException {

		String accountInfoUrlStr = MessageFormat.format(ACCOUNT_INFO_URL, serverPrefix);
		log.debug(CALLING, accountInfoUrlStr);
		String responseString = wsClient.getResponse(accountInfoUrlStr, accountId, apiKey);
		return jsonUtils.fromJson(responseString, AccountInfoResponse.class);
	}

	@Override
	public AccountInfoResponse getAccountInfo() {
		try {
			return getAccountInfo(accountId, apiKey);
		}catch(Exception e) {
			log.error("EXPTION : "+e);
			return null;
		}
		
	}

	@Override
	public CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language, String iso) throws XecdApiException {
		CurrenciesResponse response;

		String currenciesString = MessageFormat.format(CURRENCIES_URL, serverPrefix);
		currenciesString += (obsolete != null) ? "obsolete=" + obsolete : "";
		currenciesString += (language != null && !language.isEmpty()) ? "&language=" + language : "";
		currenciesString += (iso != null && !iso.isEmpty()) ? "&iso=" + iso : "";

		try {
			log.debug(CALLING, currenciesString);
			String responseString = wsClient.getResponse(currenciesString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, CurrenciesResponse.class);
		}
		catch(Exception e) {
			log.error(ERROR_CURRENCIES_FROM_XECD, e);
			throw new XecdApiException(ERROR_CURRENCIES_FROM_XECD, e);
		}

		return response;
	}
	

	public CurrenciesResponse getCurrencies2(String accountId, String apiKey, Boolean obsolete, String language, String iso) throws URISyntaxException, IOException, XecdApiException {

		String currenciesString = MessageFormat.format(CURRENCIES_URL, serverPrefix);
		currenciesString += (obsolete != null) ? "obsolete=" + obsolete : "";
		currenciesString += (language != null && !language.isEmpty()) ? "&language=" + language : "";
		currenciesString += (iso != null && !iso.isEmpty()) ? "&iso=" + iso : "";
		
		String responseString = wsClient.getResponse(currenciesString, accountId, apiKey);
		
		return jsonUtils.fromJson(responseString, CurrenciesResponse.class);
	}

	@Override
	public CurrenciesResponse getCurrencies(Boolean obsolete, String language, String iso) throws XecdApiException {
		return getCurrencies(accountId, apiKey, obsolete, language, iso);
	}

	@Override
	public CurrenciesResponse getCurrencies(String accountId, String apiKey, Boolean obsolete, String language) throws XecdApiException {
		return getCurrencies(accountId, apiKey, obsolete, language, null);
	}

	@Override
	public CurrenciesResponse getCurrencies(Boolean obsolete, String language) throws XecdApiException {
		return getCurrencies(obsolete, language, null);
	}


	@Override
	public ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException {
		ConvertFromResponse response;

		String convertFromString = MessageFormat.format(CONVERT_FROM_URL, serverPrefix, to);

		convertFromString += (from != null && !from.isEmpty()) ? FROM + from : "";
		convertFromString += (amount != null && amount != 0) ? AMOUNT + amount : "";
		convertFromString += (obsolete != null) ? OBSOLETE + obsolete : "";
		convertFromString += (inverse != null) ? INVERSE + inverse : "";

		try {
			log.debug(CALLING, convertFromString);

			String responseString = wsClient.getResponse(convertFromString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, ConvertFromResponse.class);
		}catch(Exception e) {
			log.error(ERROR_RESPONSE_FROM_XECD, e);
			throw new XecdApiException(ERROR_RESPONSE_FROM_XECD, e);
		}
		return response;
	}
	
	public ConvertFromResponse convertFrom2(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete, Boolean inverse){
		try {
			String convertFromString = MessageFormat.format(CONVERT_FROM_URL, serverPrefix, to);
			convertFromString += (from != null && !from.isEmpty()) ? FROM + from : "";
			convertFromString += (amount != null && amount != 0) ? AMOUNT + amount : "";
			convertFromString += (obsolete != null) ? OBSOLETE + obsolete : "";
			convertFromString += (inverse != null) ? INVERSE + inverse : "";
			
			String responseString = wsClient.getResponse(convertFromString, accountId, apiKey);
			return jsonUtils.fromJson(responseString, ConvertFromResponse.class);
		}catch(Exception e) {
			log.error("EXCEPTION : "+e);
			return null;
		}

	
	}

	@Override
	public ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete, Boolean inverse) {
		return convertFrom2(accountId, apiKey, from, to, amount, obsolete, inverse);
	}

	@Override
	public ConvertFromResponse convertFrom(String accountId, String apiKey, String from, String to, Double amount, Boolean obsolete) throws XecdApiException {
		return convertFrom(accountId, apiKey, from, to, amount, obsolete, null);
	}

	@Override
	public ConvertFromResponse convertFrom(String from, String to, Double amount, Boolean obsolete){
		return convertFrom(from, to, amount, obsolete, null);
	}

	@Override
	public ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException {
		ConvertToResponse response;

		String convertToString = MessageFormat.format(CONVERT_TO_URL, serverPrefix, from);

		convertToString += (to != null && !to.isEmpty()) ? "&to=" + to : "";
		convertToString += (amount != null && amount != 0) ? AMOUNT + amount : "";
		convertToString += (obsolete != null) ? OBSOLETE + obsolete : "";
		convertToString += (inverse != null) ? INVERSE + inverse : "";

		try {
			log.debug(CALLING, convertToString);

			String responseString = wsClient.getResponse(convertToString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, ConvertToResponse.class);
		}catch(Exception e) {
			log.error(ERROR_RESPONSE_FROM_XECD, e);
			throw new XecdApiException(ERROR_RESPONSE_FROM_XECD, e);
		}
		return response;
	}

	@Override
	public ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException {
		return convertTo(accountId, apiKey, to, from, amount, obsolete, inverse);
	}

	@Override
	public ConvertToResponse convertTo(String accountId, String apiKey, String to, String from, Double amount, Boolean obsolete) throws XecdApiException {
		return convertTo(accountId, apiKey, to, from, amount, obsolete, null);
	}

	@Override
	public ConvertToResponse convertTo(String to, String from, Double amount, Boolean obsolete) throws XecdApiException {
		return convertTo( to, from, amount, obsolete, null);
	}

	@Override
	public HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException {
		HistoricRateResponse response;

		String historicRateString = MessageFormat.format(HISTORIC_RATE_URL, serverPrefix, to, date);

		historicRateString += (from != null && !from.isEmpty()) ? FROM + from : "";
		historicRateString += (time != null && !time.isEmpty()) ? "&time=" + time : "";
		historicRateString += (amount != null && amount != 0) ? AMOUNT + amount : "";
		historicRateString += (obsolete != null) ? OBSOLETE + obsolete : "";
		historicRateString += (inverse != null) ? INVERSE + inverse : "";

		try {
			log.debug(CALLING, historicRateString);
			String responseString = wsClient.getResponse(historicRateString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, HistoricRateResponse.class);
		}catch(Exception e) {
			log.error(ERROR_HISTORIC_RATES_FROM_XECD, e);
			throw new XecdApiException(ERROR_HISTORIC_RATES_FROM_XECD, e);
		}


		return response;
	}

	@Override
	public HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete, Boolean inverse) throws XecdApiException {
		return historicRate(accountId, apiKey, from, to, date, time, amount, obsolete, inverse);
	}

	@Override
	public HistoricRateResponse historicRate(String accountId, String apiKey, String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException {
		return historicRate(accountId, apiKey, from, to, date, time, amount, obsolete, null);
	}

	@Override
	public HistoricRateResponse historicRate(String from, String to, String date, String time, Double amount, Boolean obsolete) throws XecdApiException {
		return historicRate(from, to, date, time, amount, obsolete, null);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException {
		HistoricRatePeriodResponse response;

		String historicRatePeriodString = MessageFormat.format(HISTORIC_RATE_PERIOD_URL, serverPrefix, to);

		historicRatePeriodString += (from != null && !from.isEmpty()) ? FROM + from : "";
		historicRatePeriodString += (amount != null && amount != 0) ? AMOUNT + amount : "";
		historicRatePeriodString += (start != null && !end.isEmpty()) ? "&start_timestamp=" + start : "";
		historicRatePeriodString += (end != null && !end.isEmpty()) ? "&end_timestamp=" + end : "";
		historicRatePeriodString += (interval != null && !interval.isEmpty()) ? "&interval=" + interval : "";
		historicRatePeriodString += (page != null && page != 0) ? "&page=" + page : "";
		historicRatePeriodString += (perPage != null && perPage != 0) ? "&per_page=" + perPage : "";
		historicRatePeriodString += (obsolete != null) ? OBSOLETE + obsolete : "";
		historicRatePeriodString += (inverse != null) ? INVERSE + inverse : "";

		try {
			log.debug(CALLING, historicRatePeriodString);
			String responseString = wsClient.getResponse(historicRatePeriodString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, HistoricRatePeriodResponse.class);
		}catch(Exception e) {
			log.error(ERROR_HISTORIC_RATES_FROM_XECD, e);
			throw new XecdApiException(ERROR_HISTORIC_RATES_FROM_XECD, e);
		}

		return response;
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete, Boolean inverse) throws XecdApiException {
		return historicRatePeriod(accountId, apiKey, from, to, amount, start, end, interval, page, perPage, obsolete, inverse);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String accountId, String apiKey, String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException {
		return historicRatePeriod(accountId, apiKey, from, to, amount, start, end, interval, page, perPage, obsolete, null);
	}

	@Override
	public HistoricRatePeriodResponse historicRatePeriod(String from, String to, Double amount, String start, String end, String interval, Integer page, Integer perPage, Boolean obsolete) throws XecdApiException {
		return historicRatePeriod(from, to, amount, start, end, interval, page, perPage, obsolete, null);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException {
		MonthlyAverageResponse response;

		String monthlyAverageString = MessageFormat.format(MONTHLY_AVERAGE_URL, serverPrefix, to);
		monthlyAverageString += (from != null && !from.isEmpty()) ? FROM + from : "";
		monthlyAverageString += (amount != null && amount != 0) ? AMOUNT + amount : "";
		monthlyAverageString += (year != null) ? "&year=" + year : "";
		monthlyAverageString += (month != null) ? "&month=" + month : "";
		monthlyAverageString += (obsolete != null) ? OBSOLETE + obsolete : "";
		monthlyAverageString += (inverse != null) ? INVERSE + inverse : "";

		try {
			log.debug(CALLING, monthlyAverageString);
			String responseString = wsClient.getResponse(monthlyAverageString, accountId, apiKey);
			response = jsonUtils.fromJson(responseString, MonthlyAverageResponse.class);
		}catch(Exception e) {
			log.error(ERROR_CURRENCIES_FROM_XECD, e);
			throw new XecdApiException("Error getting monthly average from XECD API", e);
		}
		return response;
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete, Boolean inverse) throws XecdApiException {
		return monthlyAverage(accountId, apiKey, from, to, amount, year, month, obsolete, inverse);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException {
		return monthlyAverage(from, to, amount, year, month, obsolete, null);
	}

	@Override
	public MonthlyAverageResponse monthlyAverage(String accountId, String apiKey, String from, String to, Double amount, Integer year, Integer month, Boolean obsolete) throws XecdApiException {
		return monthlyAverage(accountId, apiKey, from, to, amount, year, month, obsolete, null);
	}

}
