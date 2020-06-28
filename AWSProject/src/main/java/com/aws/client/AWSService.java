package com.aws.client;


import com.amazonaws.services.s3.AmazonS3;

public interface AWSService {

	public void createFolder(String bucketName, String folderName, AmazonS3 client);
	
}
