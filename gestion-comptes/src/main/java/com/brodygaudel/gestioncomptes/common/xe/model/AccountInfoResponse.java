package com.brodygaudel.gestioncomptes.common.xe.model;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountInfoResponse {
	private String id;
	private String organization;
	@SerializedName("package")
	private String packageLevel;
	private String service_start_timestamp;
	private String package_limit_duration;
	private Integer package_limit;
	private Integer package_limit_remaining;
	private String package_limit_reset;
}
