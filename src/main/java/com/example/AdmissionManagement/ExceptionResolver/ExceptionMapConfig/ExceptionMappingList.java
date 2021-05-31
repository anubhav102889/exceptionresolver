package com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.example.AdmissionManagement.ExceptionResolver.ExceptionResponseClass;

@Component
@ConfigurationProperties(prefix = "com.example.exceptionconfig")
public class ExceptionMappingList {

		private List<ExceptionResponseClass> list;

		public List<ExceptionResponseClass> getList() {
			return list;
		}

		public void setList(List<ExceptionResponseClass> list) {
			this.list = list;
		}
		
		
}
