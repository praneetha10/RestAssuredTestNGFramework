package api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class specBuilder {
	
	
	public static RequestSpecification getRequestspec() {
		
		//RequestSpecBuilder
	    return new RequestSpecBuilder().
	  //  .setBaseUri(System.getProperty("BASE_URI"))
	     setBaseUri("https://api.spotify.com")
	    		.setBasePath(Route.BASE_PATH).
	     setContentType(ContentType.JSON).addFilter(new AllureRestAssured()).
	     log(LogDetail.ALL). build();
		
	}
	
  public static RequestSpecification getAccountRequestspec() {
		
	  return new RequestSpecBuilder().
			 // setBaseUri(System.getProperty("BASE_URI")).
			    setBaseUri("https://accounts.spotify.com").
			     setContentType(ContentType.URLENC).addFilter(new AllureRestAssured()).
			    		 log(LogDetail.ALL).build();
			
		}
		
	public static ResponseSpecification getResponsespec() {
		
		//ResponseSpecBuilder
		  return new ResponseSpecBuilder()
				.log(LogDetail.ALL).build();
	}
}
