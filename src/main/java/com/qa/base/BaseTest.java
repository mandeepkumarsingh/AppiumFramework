package com.qa.base;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.FindsByAndroidUIAutomator;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONObject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

public class BaseTest extends TestUtils {
	public static AndroidDriver<MobileElement> driver;
	Instant startTime;
	Instant endTime;
	public InputStream dataio;
	public static HashMap<String, String> staticData = new HashMap<String, String>();
	public static JSONObject json;
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {
		/**
		 * loading the properties file
		 */
		loadProp();
		/**
		 * loading the data json file
		 */
		json=loadInputdata("data/userdata.json");
		// static datafile loading
		staticData = parseStringXML();
	}

	@Parameters({ "platformName", "platformVersion", "deviceName", "udid" })
	@BeforeTest
	public void beforeTest(String platformName, String platformVersion, String deviceName, String udid) {
		try {
			String appPath = System.getProperty("user.dir") + prop.getProperty("androidAppLocation");
			System.out.println(appPath);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			cap.setCapability(MobileCapabilityType.UDID, udid);
			cap.setCapability("appPackage", prop.getProperty("androidAppPackage"));
			cap.setCapability("appActivity", prop.getProperty("androidAppActivity"));
			cap.setCapability(MobileCapabilityType.APP, appPath);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
			URL url = new URL(prop.getProperty("appiumURL"));
			driver = new AndroidDriver<MobileElement>(url, cap);
		} catch (Exception e) {
			System.out.println("Exception Occured " + e);
			e.printStackTrace();
		}

	}

	public void waitForVisiblity(MobileElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void click(MobileElement element) {
		waitForVisiblity(element);
		element.click();
	}

	public void sendKeys(MobileElement element, String text) {
		waitForVisiblity(element);
		element.sendKeys(text);

	}

	public String getAttributes(MobileElement element, String attributes) {
		waitForVisiblity(element);
		String att = element.getAttribute(attributes);
		return att;

	}

	public boolean iselementDisplayed(MobileElement element) {
		boolean result = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(element));
			result = true;
		} catch (Exception e) {
			System.out.println("Eelement not displayed due to exception" + e);
			result = false;

		}
		return result;
	}

	public void clickElementIfPresent(MobileElement element) {
		if (iselementDisplayed(element)) {
			click(element);
		}
	}
	// scroll to the views

	public void scrollusingTouch() throws InterruptedException {

		Dimension dimension = driver.manage().window().getSize();
		int start_x = (int) (dimension.getWidth() * 0.5);
		int start_y = (int) (dimension.getHeight() * 0.8);
		int end_x = start_x;
		int end_y = (int) (dimension.getHeight() * 0.2);
		for (int i = 0; i < 3; i++) {
			Thread.sleep(3000);
			TouchAction actions = new TouchAction(driver);
			actions.press(PointOption.point(start_x, start_y))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).moveTo(PointOption.point(end_x, end_y))
					.release().perform();
			System.out.println("scrolled" + i);
		}
	}

	// scroll using actions

	public  MobileElement scrollTo(String elementText) {

		return (MobileElement) ((FindsByAndroidUIAutomator) driver).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ elementText + "\").instance(0))");
	}

	@AfterTest
	public void afterTest() {

		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
	}

}
