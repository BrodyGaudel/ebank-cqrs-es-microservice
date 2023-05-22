package com.brodygaudel.gestionoperations.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rate {
	private String quotecurrency;
	private Double mid;
}
