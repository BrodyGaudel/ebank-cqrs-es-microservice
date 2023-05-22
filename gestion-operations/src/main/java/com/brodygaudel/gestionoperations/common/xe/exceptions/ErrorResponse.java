package com.brodygaudel.gestionoperations.common.xe.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
	private int code;
	private String message;
	private String documentation_url;
}
