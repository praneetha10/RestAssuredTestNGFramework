package utils;

import java.util.Properties;

public class Configloader {
	
	private final Properties properties;
	private static Configloader configLoader;
	
	private Configloader() {
		
		properties=propertyUtils.propertyLoader("src\\test\\resources\\config.properties");
	}
	public static Configloader getInstance() {
		if(configLoader==null) {
			configLoader=new Configloader();
		}
		return configLoader;
		
	}
	public String getClientId() {
		
		String prop=properties.getProperty("client_id");
		if(prop !=null)return prop;
		else throw new RuntimeException("property client_id is not specified in the config.properties file");
	}
	
	public String getclient_secret() {
		String prop=properties.getProperty("client_secret");
		if(prop !=null)return prop;
		else throw new RuntimeException("property client_secret is not specified in the config.properties file");
	}
	public String refresh_token() {
		String prop=properties.getProperty("refresh_token");
		if(prop !=null)return prop;
		else throw new RuntimeException("property refresh_token is not specified in the config.properties file");
	}
	public String grant_type() {
		String prop=properties.getProperty("grant_type");
		if(prop !=null)return prop;
		else throw new RuntimeException("property grant_type is not specified in the config.properties file");
	}
	public String getUser() {
		String prop=properties.getProperty("User_id");
		if(prop !=null)return prop;
		else throw new RuntimeException("property User_id is not specified in the config.properties file");
	}
	
	
	   
	
}
