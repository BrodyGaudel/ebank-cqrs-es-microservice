package com.brodygaudel.gestioncomptes.common.xe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Currency {
	private String iso;
	private String currency_name;
	private Boolean is_obsolete;
	private String superseded_by;
	private String currency_symbol;
	private String currency_symbol_on_right;
}
