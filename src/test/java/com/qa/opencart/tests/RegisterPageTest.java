package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
	}

	String curMilTime;

	public String getRandomCurrTimeInMills() {
//		return curMilTime= System.currentTimeMillis()+"";
		return curMilTime = String.valueOf(System.currentTimeMillis());
	}
	
	public String getRandomEmailId() {
		return "openauto"+System.currentTimeMillis()+"@open.com";
	}

	@DataProvider
	public Object[][] gerUserResTestData() {
		getRandomCurrTimeInMills();
		return new Object[][] { { "FNTest" + curMilTime, "LNTest" + curMilTime, "fnls" + curMilTime + "@test.com",
				"9999999998", "test" + curMilTime, "Yes" },
			{"FTestName"+curMilTime,"LTestName"+curMilTime,"test"+curMilTime+"@xyz.com","9999123335","test12"+curMilTime,"No"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegSheetData() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}	

	@Test(dataProvider = "getUserRegSheetData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		Assert.assertTrue(regPage.registerUser(firstName, lastName, getRandomEmailId(), telephone,  password,  subscribe));
		
	}
}
