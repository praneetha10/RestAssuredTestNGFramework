package Tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.spotify.oauth2.pojo.ErrorMain;
import com.spotify.oauth2.pojo.InnerError;
import com.spotify.oauth2.pojo.Playlist;

import ApplicationAPi.PlaylistAPI;
import api.StatusCode;


import static api.specBuilder.getRequestspec;
import static api.specBuilder.getResponsespec;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.Dataloader;
import utils.Fakers;

@Epic("Spotify Oauth 2.0")
@Feature("PlaylistTests")
@Test
public class SpotifyOauth2 extends BaseTest {
	
	public Playlist playlistBuilder(String name,String description,boolean _public) {
	
		Playlist playlist=new Playlist();   //without builder
		playlist.setName(name);
		playlist.setDescription(description);
		playlist.set_public(_public);
		return playlist;
		
		
		/*return Playlist.builder()     //with builder
		.name(name)
		.description(description)
		._public(_public).build();*/

		
		/*return new Playlist().setName(name). //without lombok
				setDescription(description).
				setPublic(_public);*/
	}

	
	public void assertPlayListEqual(Playlist responsePlaylist,Playlist requestPlaylist) {
		
		assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
	}
	

	public void assertStatusCode(int actualStatusCode,StatusCode statuscode) {
		assertThat(actualStatusCode,equalTo(statuscode.code));
		
	}

	public void assertError(ErrorMain responseErr,StatusCode statusCode) {
		
		assertThat(responseErr.getError().getStatus(),equalTo(statusCode.code));
		assertThat(responseErr.getError().getMessage(),equalTo(statusCode.msg));
	}
	
	
	@Story("Create a playlist story")
	@Link("https://example.org")
	@Link(name="allure",type="mylink")
	@TmsLink("12345")
	@Issue("1234567")

	@Description("this is the description")
	@Test(description="should be able to create a playlist")
	public void shouldBeAbleToCreateAPlaylist() {
		
		Playlist requestPlaylist = playlistBuilder(Fakers.generateName(),Fakers.generateDescription(),false);
		
		Response response=PlaylistAPI.post(requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_201);
	    assertPlayListEqual(response.as(Playlist.class),requestPlaylist);
		
	}
	@Step()
	@Test
	public void shouldBeAbleToGetAPlaylist() {
		
		Playlist requestPlaylist=playlistBuilder(Fakers.generateName(),Fakers.generateDescription(),true);
		
		Response response=PlaylistAPI.get(Dataloader.getInstance().getplaylistId());
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
	
		assertPlayListEqual(response.as(Playlist.class),requestPlaylist);
	}
	@Step
	@Test
	public void shouldBeAbleToUpdateAPlaylist() {
		
		
		Playlist requestPlaylist=playlistBuilder(Fakers.generateName(),Fakers.generateDescription(),false);
		
		
		Response response=PlaylistAPI.update(Dataloader.getInstance().updatePlaylistId(), requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
		
	}
	//Negative scenarios 
	//1.without providing name.
	//2.expired access token we should get 401
	//1.without providing name.
	@Step
	@Story("Create a playlist story")
	@Test
	public void ShouldNotBeAbleTOCreateAPlaylistWithName() {	
		Playlist requestPlaylist=playlistBuilder("",Fakers.generateDescription(),false);
		Response response=PlaylistAPI.post(requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_400);
		assertError(response.as(ErrorMain.class),StatusCode.CODE_400);
		}
	@Step()
	@Story("Create a playlist story")
	@Test
	 public void shouldNotBeAbleToCreateAPlayListWith() {
		
		String invalid_token="12345";
		
		Playlist requestPlaylist=playlistBuilder(Fakers.generateName(),Fakers.generateDescription(),false);
		//Here we are using post call so reuable post method
		Response response=PlaylistAPI.post(invalid_token,requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_401);
		assertError(response.as(ErrorMain.class),StatusCode.CODE_401);
		 
	}
}
