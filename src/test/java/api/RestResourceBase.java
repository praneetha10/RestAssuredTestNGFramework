package api;

import static api.specBuilder.getRequestspec;
import static api.specBuilder.getResponsespec;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResourceBase {
	
	//Resusable post call
	public static Response post(String path,String token,Object requestPlaylist) {
		return given(getRequestspec())
				.body(requestPlaylist).
				auth().oauth2(token).
			    when().post(path).
		then().spec(getResponsespec()).extract().response();
	}
	
	//Reusable Token
	
	public static Response postAccount(HashMap<String,String>formParams) {
		return given(specBuilder.getAccountRequestspec()).
		formParams(formParams).
		when().post(Route.API+Route.Token).then().
		spec(specBuilder.getResponsespec()).extract().response();
	}
	
	//Reusable get call
	public static Response get(String path,String token){
		
		return given(getRequestspec()).auth().oauth2(token).
				 
				when().get(path).
		then().spec(getResponsespec()).
		extract().response();
	}
	
	//Reusable update 
	public static Response update(String path,String token,Object requestPlaylist) {
		
			return given(getRequestspec()).auth().oauth2(token).
				body(requestPlaylist).
				when().put(path).
				then().spec(getResponsespec()).extract().response();
	}
	
	
	
}


	
	
	


