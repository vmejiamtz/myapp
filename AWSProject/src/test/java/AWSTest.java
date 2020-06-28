import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aws.client.AWSController;

public class AWSTest {

	@Autowired
	private AWSController awsController;
	
	@Test
	public void crearFolderTest() {
		
		String folderName ="Test";
		awsController.gotoS3Bucket(folderName);
	}
}
