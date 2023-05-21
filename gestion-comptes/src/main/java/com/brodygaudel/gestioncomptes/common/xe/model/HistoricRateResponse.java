package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoricRateResponse {
	private String terms;
	private String privacy;
	private String from;
	private Double amount;
	private Date timestamp;
	private List<Rate> to;
}
