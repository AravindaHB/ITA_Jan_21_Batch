package com.ita.selenium.actitime.util;

public class ActitimeUtils extends DriverUtils
{
	
	
	public static void launch(String url)
	{
		System.out.println("--- Launching Application " + url);
		driver.get(url);
		
	}

}
