package com.aws.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class Amazons3Example {


		private static final String SUFFIX = "/";
		
		public static void main(String[] args) {
		
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAVVHBOSNPBWELC6XW", 
					"6YEj64YL/oCPs3/HFk7g0fkdlycQAunSqeExE1pg");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "myawslocalbucket";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				System.out.println(" - " + bucket.getName());
			}
			
			// create folder into bucket
			String folderName = "testfolder";
			createFolder(bucketName, folderName, s3client);
			
			// upload file to folder and set it to public
			String fileName = folderName + SUFFIX + "testPersonalFile.txt";
			s3client.putObject(new PutObjectRequest(bucketName, fileName, 
					new File("C:\\Users\\verov\\OneDrive\\Escritorio\\testPersonalFile.txt"))
					.withCannedAcl(CannedAccessControlList.PublicRead));
			
			
		}
		
		public static void createFolder(String bucketName, String folderName, AmazonS3 client) {
			// create meta-data for your folder and set content-length to 0
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			// create empty content
			InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
			// create a PutObjectRequest passing the folder name suffixed by /
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
					folderName + SUFFIX, emptyContent, metadata);
			// send request to S3 to create folder
			client.putObject(putObjectRequest);
		}
		
	

}
