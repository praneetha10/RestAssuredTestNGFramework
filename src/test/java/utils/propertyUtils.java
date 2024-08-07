package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class propertyUtils {
	
	public static Properties propertyLoader(String filePath) {
		
		Properties  properties =new Properties(); //Create object for properties class
		BufferedReader reader;
		try {
			reader=new BufferedReader(new FileReader(filePath));
			try {
				properties.load(reader);
				reader.close();
			}catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("failed to load properites file"+ filePath);
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at"+ filePath);
		}
		
		return properties;
		
		
		
	}
	

}
