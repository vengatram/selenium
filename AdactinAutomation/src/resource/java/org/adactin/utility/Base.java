package org.adactin.utility;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	static WebDriver driver;
	WebDriverWait wait;

	static File f1 = new File("./JSON/Configuration.json");

	public static WebDriver getDriver() {
		JSONObject jsonObject = JSONReadFromFile();
		String browser = (String) jsonObject.get("browser");

		File f = new File("./driver");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", f.getAbsolutePath()
					+ "/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", f.getAbsolutePath()
					+ "/geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", f.getAbsolutePath()
					+ "/IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		}

		driver.manage().window().maximize();
		driver.get((String) jsonObject.get("url"));
		return driver;
	}

	public boolean elementToBeVisible(WebDriver driver, int time,
			WebElement element) {
		boolean flag = false;
		try {
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(element));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean elementFound(WebDriver driver, int time, WebElement element) {
		boolean res = false;
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		try {
			res = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;

	}

	public boolean elementFound(WebElement element) {
		boolean b = false;
		try {
			b = element.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return b;

	}

	public void setText(WebElement element, String name) {
		if (name != null && elementFound(element)) {
			element.clear();
			element.sendKeys(name);
		}

	}

	public String getText(WebElement element) {
		String name = null;
		if (elementFound(element)) {
			name = element.getAttribute("value");

		}
		return name;
	}

	public void clickBtn(WebElement element) {
		if (elementFound(element)) {
			element.click();
		}

	}
	public static JSONObject JSONReadFromFile() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {

			Object obj = parser.parse(new FileReader(f1.getAbsoluteFile()));

			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	public void dropDownSelect(WebElement element,String name) {
		Select s=new Select(element) ;
		s.selectByValue(name);
	}
	public void dropDownSelectVText(WebElement element,String name) {
		Select s=new Select(element) ;
		s.selectByVisibleText(name);
	}
	public void getScreenShot(String screenShotFileName) {
		File screenShotLocation = new File("./screenshot/" + screenShotFileName
				+ ".png");
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, screenShotLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
}}
