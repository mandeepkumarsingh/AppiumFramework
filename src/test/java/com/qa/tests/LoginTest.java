package com.qa.tests;

import org.testng.annotations.Test;

import com.qa.Pages.LoginPage;
import com.qa.Pages.LottiePage;
import com.qa.base.BaseTest;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

public class LoginTest extends BaseTest {
	LottiePage lottie;
	LoginPage loginpage;

	@BeforeMethod
	public void beforeMethod(Method method) {
		lottie = new LottiePage();
		loginpage = new LoginPage();
		System.out.println("****** Starting the Test ******  " + method.getName());

	}

	@Test(priority = 1)
	public void invalidLogin() {
		if (lottie.verifyLottieScreenIfVisible()) {
			lottie.lottieScreenNavigationIfDisplayed();
		}
		loginpage.enterMobileNo(json.getJSONObject("invalidDetails").getString("mobile"));
		loginpage.clickContinue();
		loginpage.ifLocationScreenPresentthenClickSkip();
		loginpage.enterOTP(json.getJSONObject("invalidDetails").getString("otp"));
		boolean error = loginpage.visiblityOfResendOtp();
		System.out.println(error);
		Assert.assertTrue(error, "loggedIn with invalid Otp");
		String str = staticData.get("Title_Text");
		System.out.println(str);
	}
	// clickOnEdit

	@Test(priority = 2)
	public void validLogin() throws InterruptedException {
		
		boolean dtr = loginpage.clickOnEdit();
		loginpage.clickContinue();
		loginpage.ifLocationScreenPresentthenClickSkip();
		loginpage.enterOTP(json.getJSONObject("validDetails").getString("otp"));
		Thread.sleep(1000);
		 

	}
}
