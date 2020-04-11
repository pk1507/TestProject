package com.basic.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

	public static WebDriver getDriver(String browserType)
	{
		if(browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\java\\com\\basic\\browsers\\geckodriver.exe");
			return new FirefoxDriver();
		}else if(browserType.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\java\\com\\basic\\browsers\\chromedriver.exe");
			return new ChromeDriver();
		}
		return null;
	}

}


