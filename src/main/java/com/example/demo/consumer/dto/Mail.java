package com.example.demo.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class Mail {
	private String from;
	private String to;
	private String subject;
	private String text;
}
