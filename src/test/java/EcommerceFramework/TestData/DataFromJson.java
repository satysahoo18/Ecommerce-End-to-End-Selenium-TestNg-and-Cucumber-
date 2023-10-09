package EcommerceFramework.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataFromJson {
	
	@Test
	public List<HashMap<String, String>> dataFromFile() throws IOException{
		String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//EcommerceFramework//TestData//TestDataJson.json"), 
				StandardCharsets.UTF_8);
		
		//Using Jackson Databind Package
		ObjectMapper obj =new ObjectMapper();
		List <HashMap<String,String>> value = obj.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return value;
	}
	
}
