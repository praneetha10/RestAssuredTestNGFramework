package utils;

import com.github.javafaker.Faker;

public class Fakers {
	
	
	public static  String generateName() {
		
		Faker faker=new Faker();
		return "Playlist" +faker.regexify("[A-Za-z0-9 ,_-]{10}");
	}
	
	public static String generateDescription() {
		
		Faker faker=new Faker();
		return "Description" +faker.regexify("[A-Za-z0-9 ,_-]{10}");
	}
}
