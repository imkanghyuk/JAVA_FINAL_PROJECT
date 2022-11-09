package com.example.demo.consumer.exception;

import lombok.*;

@AllArgsConstructor
public class ConsumerJobFailException extends RuntimeException {
	String message;
	
	@Override
	public String getMessage() {
		return message;
	}
}
