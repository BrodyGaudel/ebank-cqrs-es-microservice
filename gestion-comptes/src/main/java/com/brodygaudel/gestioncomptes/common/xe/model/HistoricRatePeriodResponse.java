package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoricRatePeriodResponse {
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Map<String, List<HistoricRate>> to;
}
