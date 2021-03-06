package com.example.EmployeeSb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecourseNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RecourseNotFoundException(String message) {
		super(message);
	}

}
