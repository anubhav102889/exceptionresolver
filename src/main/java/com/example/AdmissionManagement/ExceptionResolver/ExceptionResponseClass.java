package com.example.AdmissionManagement.ExceptionResolver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponseClass {

	private String message;
	
	@JsonProperty("errorCode")
	private Integer statusCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
