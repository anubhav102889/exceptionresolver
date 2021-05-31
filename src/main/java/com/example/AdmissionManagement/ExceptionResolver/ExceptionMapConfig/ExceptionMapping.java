package com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.AdmissionManagement.ExceptionResolver.ExceptionResponseClass;

@Component
@ConfigurationProperties(prefix = "com.example.exceptionconfig")
public class ExceptionMapping {
	
	private Map<String,Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	

}
