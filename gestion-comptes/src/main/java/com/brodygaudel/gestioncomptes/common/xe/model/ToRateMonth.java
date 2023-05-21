package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ToRateMonth {
	private Double monthlyAverage;
	private Double monthlyAverageInverse;
	private Integer month;
	private Integer daysInMonth;
}
