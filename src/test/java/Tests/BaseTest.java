package Tests;

import java.lang.reflect.Method;


import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		
		System.out.println("StARTING Test: " + m.getName());
		System.out.println("THREAD ID" + Thread.currentThread().getId());
		
	}
	

}
