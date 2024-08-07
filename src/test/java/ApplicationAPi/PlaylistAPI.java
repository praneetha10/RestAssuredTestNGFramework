package ApplicationAPi;

import static api.specBuilder.getRequestspec;
import static api.specBuilder.getResponsespec;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.pojo.Playlist;

import api.RestResourceBase;
import   api.Route;
import api.TokenManager;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.Configloader;

public class PlaylistAPI {
	
	//static  String access_token="BQB29JE4cdu620CL0Yk4DtAD2W4BLIbiZSOAO8o_ESNnRQ-eA5T5XiVJKYMpx34CzaeQRFVqT4R9KmH_CscZDMJqiwi5kRrBYKehF6NosIyZiQkYWniEaAOgr4WOWUz3yIjQ8BWjIDYJz-AJvw0hapNyCD5fBL1gt4Ioh4PqJK4_dUlqKKP1dglEHU-gKmMj5uR4uzSk_IP2xwRgvjheAHLjl3HRqHR8IBhJZGrr02hJqvQenWWDP7pBK5P-58wYMkHie5_aIfBBXI2-3OQgBA";
	
	//Resusable post call
	@Step
	public static Response post(Playlist requestPlaylist) {
		
		return RestResourceBase.post(Route.USERS+"/"+Configloader.getInstance().getUser()+
				Route.PLATLISTS,TokenManager.getToken(),requestPlaylist);
		
	}
	
	//Overload the invalid token
	public static Response post(String token,Playlist requestPlaylist) {
		
		return RestResourceBase.post(Route.USERS+"/"+Configloader.getInstance().getUser()+
				Route.PLATLISTS,token, requestPlaylist);
		
		
	}
	
	//Reusable get call
	public static Response get(String playlistId){
		return RestResourceBase.get(Route.PLATLISTS+"/"+playlistId,TokenManager.getToken());
		
	}
	
	//Reusable update 
	public static Response update(String playlistId,Playlist requestPlaylist) {
		return RestResourceBase.update(Route.PLATLISTS+"/"+ playlistId, TokenManager.getToken(),requestPlaylist);
			
	}
	
	
	
}


	
	
	


