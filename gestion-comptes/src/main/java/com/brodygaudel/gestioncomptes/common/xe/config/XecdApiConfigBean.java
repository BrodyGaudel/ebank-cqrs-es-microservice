package com.brodygaudel.gestioncomptes.common.xe.config;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class XecdApiConfigBean {

	private String accountId;
	private String apiKey;
	private String serverPrefix;
	private Integer connectTimeout;

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
