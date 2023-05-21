package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MonthlyAverageResponse {
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Integer year;
	private Map<String, List<ToRateMonth>> to;
}
