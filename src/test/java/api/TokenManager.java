package api;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Configloader;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken() {
		
		try {
			
			if(access_token==null||Instant.now().isAfter(expiry_time)) {
				Response response=renewToken();
				access_token=response.path("access_token");
				int expiryDurationInSeconds=response.path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds-300);  //now()- to get present time.
				  //plusSecound -Add seconds to it.
			}
			else {
				System.out.println("Token is good to use");
			}
		}
		catch(Exception e){
			
			throw new RuntimeException("ABORT!!! Failed to get token");
			
		}
		return access_token;
		
	}																   
	
	private static Response renewToken() {
		HashMap<String,String> formParams=new HashMap<String,String>();
		formParams.put("client_id", Configloader.getInstance().getClientId());
		formParams.put("client_secret", Configloader.getInstance().getclient_secret());
		formParams.put("refresh_token",Configloader.getInstance().refresh_token());
		formParams.put("grant_type",Configloader.getInstance().grant_type());
		
		Response response=RestResourceBase.postAccount(formParams);
		
		if(response.statusCode()!=200) {
			
			throw new RuntimeException("ABORT!!!! Renew Token failed");
		}
		return response;
		
		
	}
	
	
	

}
