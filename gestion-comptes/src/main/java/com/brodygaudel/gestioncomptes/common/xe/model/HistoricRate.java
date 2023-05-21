package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HistoricRate {
	private String terms;
	private String privacy;
	private Double mid;
	private Date timestamp;
}
