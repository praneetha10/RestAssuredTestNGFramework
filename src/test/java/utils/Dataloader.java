package utils;

import java.util.Properties;

public class Dataloader {
	
	private final Properties properties;
	private static Dataloader dataLoader;
	
	private Dataloader() {
		
		properties=propertyUtils.propertyLoader("src\\test\\resources\\data.properties");
	}
	public static Dataloader getInstance() {
		if(dataLoader==null) {
			dataLoader=new Dataloader();
		}
		return dataLoader;
		
	}
	public String getplaylistId() {
		
		String prop=properties.getProperty("get_playlist_id");
		if(prop !=null)return prop;
		else throw new RuntimeException("property client_id is not specified in the config.properties file");
	}
	
	public String updatePlaylistId() {
		String prop=properties.getProperty("update_playlist_id");
		if(prop !=null)return prop;
		else throw new RuntimeException("property client_id is not specified in the config.properties file");
	}
}
