package com.example.AdmissionManagement.ExceptionResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig.ExceptionMapValueAsClass;
import com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig.ExceptionMapping;
import com.example.AdmissionManagement.ExceptionResolver.ExceptionMapConfig.ExceptionMappingList;


@ControllerAdvice
@PropertySource(value = { "classpath:exceptionmapping.yml" }, factory = YamlPropertySourceConfig.class)
public class MyexceptionResolver<T> extends ResponseEntityExceptionHandler{
	
	@Value("${app.name}")
	private String name;
	
	//@Autowired
	private final ExceptionMapValueAsClass exceptionMapValueAsClass;
	//@Autowired
	private final ExceptionMapping exceptionMapping;
	
	//@Autowired
	private final ExceptionMappingList exceptionMappingList;
	
	public MyexceptionResolver(ExceptionMapValueAsClass exceptionMapValueAsClass, ExceptionMapping exceptionMapping,
			ExceptionMappingList exceptionMappingList) {
		super();
		this.exceptionMapValueAsClass = exceptionMapValueAsClass;
		this.exceptionMapping = exceptionMapping;
		this.exceptionMappingList = exceptionMappingList;
	}

	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){
		ExceptionResponseClass response=new ExceptionResponseClass();
		StringBuilder message =new StringBuilder("Http Method "+ex.getMethod()+" not supported. Supported Methods Are: ");
		
		ex.getSupportedHttpMethods().stream().forEach(httpMethod->{
			
			message.append(httpMethod.toString()+",");
		});
		System.out.println(name);
		
		response.setMessage(message.substring(0, message.length()-1));
		response.setStatusCode(status.value());
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String,String> fieldErrorMap=new HashMap<>();
		
		ex.getFieldErrors().stream().forEach(fe->{
			fieldErrorMap.put(fe.getField(), fe.getDefaultMessage());
		});
		return new ResponseEntity<>(fieldErrorMap, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object>handleNoHandlerFoundException(NoHandlerFoundException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request){
		System.out.println("HttpNotFound");
		ExceptionResponseClass exc=exceptionMapValueAsClass.getMapValueasClass()
				.get("NO_HANDLER_FOUND");
		System.out.println(exc);
		ExceptionResponseClass exceptionResponseClass=new ExceptionResponseClass();
		System.out.println(exceptionMappingList.getList());
		exceptionResponseClass.setMessage(exceptionMapping.getMap().get("NO_HANDLE_FOUND_MESS")+" "+ex.getRequestURL());
		exceptionResponseClass.setStatusCode((Integer) exceptionMapping.getMap().get("NO_HANDLER_FOUND_ERRORCODE"));
		return new ResponseEntity<Object>(exceptionResponseClass, HttpStatus.NOT_FOUND);
	}
	

}

