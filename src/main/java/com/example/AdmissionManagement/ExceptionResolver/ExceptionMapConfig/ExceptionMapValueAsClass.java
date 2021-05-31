package com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.AdmissionManagement.ExceptionResolver.ExceptionResponseClass;

@Component
@ConfigurationProperties(prefix = "com.example.exceptionconfig")
public class ExceptionMapValueAsClass {
	
	private Map<String,ExceptionResponseClass> mapValueasClass;

	public Map<String, ExceptionResponseClass> getMapValueasClass() {
		return mapValueasClass;
	}

	public void setMapValueasClass(Map<String, ExceptionResponseClass> mapValueasClass) {
		this.mapValueasClass = mapValueasClass;
	}

	

	

}
