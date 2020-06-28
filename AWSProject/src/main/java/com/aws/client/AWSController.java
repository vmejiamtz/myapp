package com.aws.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

@Controller
@RequestMapping("/aws")
public class AWSController {

	@Autowired
	private AWSService awsService;
	
	
	@RequestMapping(value = "/createFolderonS3Bucket", method = RequestMethod.GET)
	@ResponseBody
	public String gotoS3Bucket(@RequestParam(required=false, name="folderName") String folderName) {
		
		String bucketName = "myawslocalbucket";

	
		
		AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAVVHBOSNPBWELC6XW", 
				"6YEj64YL/oCPs3/HFk7g0fkdlycQAunSqeExE1pg");
		
		// create a client connection based on credentials
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		awsService.createFolder(bucketName, folderName, s3client);
		return "Creando folder en S3 Bucket de Amazon";
	}
}
