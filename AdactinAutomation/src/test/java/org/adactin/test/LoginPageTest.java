package org.adactin.test;

import org.adactin.page.LoginPage;
import org.adactin.utility.Base;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginPageTest extends Base{
	static WebDriver driver;
	LoginPage login;

	@BeforeClass
	public static void launchBrowser() {
		driver=Base.getDriver();

	}

	@Test
	public void averifyLogin() {
		login = new LoginPage(driver);
		setText(login.getTxtUserName(), "vengat16");
		setText(login.getTxtUserPassword(), "Karthick");
		login.getTxtUserPassword().sendKeys(Keys.ENTER);
		elementToBeVisible(driver, 20, login.getBtnLoginButton());
		clickBtn(login.getBtnLoginButton());
		System.out.println("Success");

	}

	

	@AfterClass
	public static void closeBrowser() {
		driver.quit();

	}

}
