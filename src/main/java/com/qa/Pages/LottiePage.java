package com.qa.Pages;

import com.qa.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LottiePage extends BaseTest {



	@AndroidFindBy(id = "com.lenskart.app:id/button_english")
	public MobileElement clickEnglishLanguageButton;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_continue")
	private MobileElement clickSelfieLottieScreenOne;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_continue")
	private MobileElement clickTryFramesLottieScreenTwo;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_continue")
	private MobileElement clickStartShoppingLottieScreenThree;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_grant_permission")
	private MobileElement clickGrantPermissionButton;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private MobileElement clickAllowCameraPermissionButton;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private MobileElement clickAllowStoragePermissionButton;

	@AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private MobileElement clickAllowLocationPermissionButton;

	@AndroidFindBy(id = "com.lenskart.app:id/title")
	private MobileElement lottiePageEnglistTitleText;

	@AndroidFindBy(id = "com.lenskart.app:id/title_hindi")
	private MobileElement lottiePageHindiTitleText;

	@AndroidFindBy(id = "com.lenskart.app:id/button_hindi")
	private MobileElement lottiePageHindiButton;

	@AndroidFindBy(id = "com.lenskart.app:id/splash_animation")
	private MobileElement lenskartSplashScreen;

	public LoginPage lottieScreenNavigation() {

		click(clickEnglishLanguageButton);
		//utils.log().info("Selected English as language");

		click(clickSelfieLottieScreenOne);
		//utils.log().info("Clicked on Selfie continue button");

		click(clickTryFramesLottieScreenTwo);
		//utils.log().info("Clicked on Try Frames continue button");

		click(clickStartShoppingLottieScreenThree);
		//utils.log().info("Clicked on Start Shopping continue button");

		click(clickGrantPermissionButton);
		//utils.log().info("Clicked on Grant Permission button");

		click(clickAllowCameraPermissionButton);
		//utils.log().info("Clicked on Allow Camera Permission button");

		click(clickAllowStoragePermissionButton);
		//utils.log().info("Clicked on Allow Storage Permission button");

		click(clickAllowLocationPermissionButton);
		//utils.log().info("Clicked on Allow Location Permission button");

		return new LoginPage();
	}



	public boolean verifyLottieScreenIfVisible() {
		return iselementDisplayed(clickEnglishLanguageButton);
	}

	public LoginPage lottieScreenNavigationIfDisplayed() {

		click(clickEnglishLanguageButton);

		return new LoginPage();
	}

}


