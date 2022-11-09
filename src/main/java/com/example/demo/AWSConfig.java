package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
	private String iamAccessKey = "AKIA6J6PBT2X7FMKQYKQ"; // IAM Access Key
	private String iamSecretKey = "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"; // IAM Secret Key
	private String region = "ap-northeast-2"; // Bucket Region 
	
	@Bean
	public AmazonS3Client amazonS3Client() {
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(iamAccessKey, iamSecretKey);
		return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                                                             .withRegion(region)
                                                             .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                                                             .build();
	}
}
