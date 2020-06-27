package com.aws.client;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class ClientServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String SUFFIX = "/";

		AWSCredentials credentials = new BasicAWSCredentials("AKIAVVHBOSNPBWELC6XW", "6YEj64YL/oCPs3/HFk7g0fkdlycQAunSqeExE1pg");

		ClientConfiguration clientConfig = new ClientConfiguration();

		AmazonS3 s3Client = AmazonS3ClientBuilder
				.standard()
				.withRegion(Regions.US_WEST_1)
				.withClientConfiguration(clientConfig)
				//.withCredentials(new AWSStaticCredentialsProvider(credentials))
				//.disableChunkedEncoding()
				.build();
		
	
	
		// create meta-data for your folder and set content-length to 0
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(0);
		// create empty content
		InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
		// create a PutObjectRequest passing the folder name suffixed by /
		PutObjectRequest putObjectRequest = new PutObjectRequest("myawslocalbucket", "testFolder" + SUFFIX,
				emptyContent, metadata);
		// send request to S3 to create folder
		s3Client.putObject(putObjectRequest);

		String finalFolderName = "testFolder" + SUFFIX;
		String fileName = finalFolderName.concat("testPersonalFile.txt");

		File file = new File("C:\\Users\\verov\\OneDrive\\Escritorio\\testPersonalFile.txt");
		
		                                                                         
		

		TransferManager transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();

		try {
			
			
			PutObjectRequest fileRequest = new PutObjectRequest("myawslocalbucket", fileName, file).withCannedAcl(CannedAccessControlList.PublicRead);
			
			// InputStream is= file.getInputStream();
			// PutObjectRequest fileRequest = new PutObjectRequest("myawslocalbucket", fileName, is,new ObjectMetadata());
			ObjectMetadata fileMetadata = new ObjectMetadata();
			fileMetadata.addUserMetadata("title", "Test from personal laptop");
			fileRequest.setMetadata(fileMetadata);

			s3Client.putObject(fileRequest);
			Upload upload = transferManager.upload(fileRequest);

			try {
				upload.waitForUploadResult();
			} catch (AmazonClientException e) {
				System.out.println("error");
			} catch (InterruptedException e) {
				System.out.println("error");
			}

		} catch (AmazonServiceException e) {
			System.out.println("error");
		}

	}

}
