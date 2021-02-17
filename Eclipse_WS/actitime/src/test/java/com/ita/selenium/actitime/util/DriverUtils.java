package com.ita.selenium.actitime.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverUtils {

	public static WebDriver driver = null;

	public static WebDriver getDriver() {
		System.out.println("--- Creating WebDriver Object ---");
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver getDriver(String type) {
		System.out.println("--- Creating WebDriver Object ---" + type);
		switch (type.toLowerCase())
		{
			case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
				break;
			case "ff":
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				break;
			case "edge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
				break;
	
			default:
				System.out.println("Contact framework develooper for the browser type - " + type);
				break;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public static WebElement getElement(String identifier, String value) {
		WebElement ele = null;
		boolean flag = false;
		System.out.println("--- Finding the element using " + identifier + " and " + value);
		switch (identifier.toLowerCase()) {
		case "id":
			ele = driver.findElement(By.id(value));
			break;
		case "name":
			ele = driver.findElement(By.name(value));
			break;
		case "classname":
			ele = driver.findElement(By.className(value));
			break;
		case "tagname":
			ele = driver.findElement(By.tagName(value));
			break;
		case "linktext":
			ele = driver.findElement(By.linkText(value));
			break;
		case "partiallinktext":
			ele = driver.findElement(By.partialLinkText(value));
			break;
		case "css":
			ele = driver.findElement(By.cssSelector(value));
			break;
		case "xpath":
			ele = driver.findElement(By.xpath(value));
			break;
		default:
			System.out.println("Please check ur identifier passed!!! or contact FW developer");
			break;
		}

		if (ele.isDisplayed()) {
			System.out.println("*** Element is displayed ***");
			if (ele.isEnabled()) {
				System.out.println("*** Element is Enabled ***");
				flag = true;
			} else {
				System.out.println("Element is not enabled so returning null");
			}
		} else {
			System.out.println("Element is not dispalyed in applicaiton returing null");
		}

		if (flag) {
			return ele;
		} else {
			return null;
		}
	}

	public static void type(String identifier, String value, String txt) {
		System.out.println("--- Performing type on " + identifier + " and " + value + " and value is " + txt);
		getElement(identifier, value).sendKeys(txt);
	}

	public static void click(String identifier, String value) {
		System.out.println("--- Performing click on " + identifier + " and " + value);
		getElement(identifier, value).click();
	}

	public static String getText(String identifier, String value) {
		System.out.println("--- getting text on" + identifier + " and " + value);
		String txt = getElement(identifier, value).getText();
		System.out.println("text found - " + txt);
		return txt;
	}

}