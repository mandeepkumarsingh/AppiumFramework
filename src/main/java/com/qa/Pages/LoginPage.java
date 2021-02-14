package com.qa.Pages;

import org.openqa.selenium.By;

import com.qa.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	// Mobile only login flow
	@AndroidFindBy(id = "com.lenskart.app:id/input_phone_number")
	private MobileElement mobileNumberTextfield;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_continue")
	private MobileElement mobileContinueButton;

	@AndroidFindBy(id = "com.lenskart.app:id/user_name")
	private MobileElement enterNameTextfield;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_continue")
	private MobileElement enterNameContinueButton;

	@AndroidFindBy(id = "com.lenskart.app:id/pin_otp_code")
	private MobileElement enterMobileOTPTextfield;

	@AndroidFindBy(id = "com.lenskart.app:id/title")
	private MobileElement loginPageTitleId;

	@AndroidFindBy(id = "com.lenskart.app:id/image")
	private MobileElement loginPageProfileImageId;

	@AndroidFindBy(id = "com.lenskart.app:id/view")
	private MobileElement loginPageImageId;

	@AndroidFindBy(id = "com.lenskart.app:id/check_box_whatsaap_opt_in")
	private MobileElement loginPageWhatAppsCheckBoxId;

	@AndroidFindBy(id = "com.lenskart.app:id/image_whatsapp")
	private MobileElement loginPageWhatAppsImageId;

	@AndroidFindBy(id = "com.lenskart.app:id/link_tnc")
	private MobileElement loginPageTnCId;

	@AndroidFindBy(id = "com.lenskart.app:id/input_country_code")
	private MobileElement loginPageCountryCodeInputId;

	@AndroidFindBy(xpath = "//android.widget.CheckBox[@checked = 'true']")
	private MobileElement loginPageWhatsAppCheckedXpath;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index = '1']")
	private MobileElement loginPageTnCXpath;

	@AndroidFindBy(xpath = ".//*[contains(@text,'Wrong OTP entered')]")
	private MobileElement wrongOTPToastMessage;

	@AndroidFindBy(id = "com.lenskart.app:id/btn_edit_phone")
	private MobileElement editButton;

	@AndroidFindBy(id = "com.lenskart.app:id/text_do_it_later")
	private MobileElement skipLocation;
	@AndroidFindBy(xpath = ".//*[contains(@text,'RESEND CODE')]")
	private MobileElement reSendOtp;

	public void enterMobileNo(String mobile) {
		sendKeys(mobileNumberTextfield, mobile);

	}

	public void clickContinue() {
		click(mobileContinueButton);
	}

	public void enterOTP(String otp) {

		sendKeys(enterMobileOTPTextfield, otp);
	}

	public boolean visiblityOfResendOtp() {
		boolean reult = false;
		try {
			waitForVisiblity(reSendOtp);
			reult = true;
		} catch (Exception e) {
			// TODO: handle exception
			reult = false;

		}
		return reult;
	}

	public void ifLocationScreenPresentthenClickSkip() {
		clickElementIfPresent(skipLocation);
	}

	public boolean clickOnEdit() {
		boolean result = false;
		try {
			click(editButton);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public Homepage login(String mobile,String OTP) {
		
		enterMobileNo(mobile);
		clickContinue();
		ifLocationScreenPresentthenClickSkip();
		enterOTP(OTP);
		return new Homepage();
	}

}
