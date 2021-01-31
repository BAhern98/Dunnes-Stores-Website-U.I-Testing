package main;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

class CreateAccountTestDocker {

	static WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		String path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		cap.setBrowserName(BrowserType.CHROME);
		driver.get("https://www.dunnesstores.com/");
		driver.manage().window().maximize();
	}

	@Test
	void test() {
		new WebDriverWait(driver, 20)
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#onetrust-accept-btn-handler")))
				.click();
		new WebDriverWait(driver, 20).until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("ul.global-subnav__list>li:nth-of-type(5)")))
				.click();

		WebElement element = driver.findElement(By.xpath("//select[@id = 'title']"));

		Select sel = new Select(element);
		sel.selectByVisibleText("Mr");
		driver.findElement(By.id("firstName")).sendKeys("Brendan");
		driver.findElement(By.id("lastName")).sendKeys("Ahern");
		driver.findElement(By.id("mobile")).sendKeys("0861786033");
		driver.findElement(By.id("newPassword")).sendKeys("Googl111");
		driver.findElement(By.id("confirmPassword")).sendKeys("Googl111");

		WebElement checkBox = driver.findElement(By.id("registryTandCs"));
		Actions action = new Actions(driver);
		action.moveToElement(checkBox).click().build().perform();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[@class='btn btn--stretch btn--primary btn--green t-auth__login--btn' and contains(., 'Create my account')]")))
				.click();
		String actualError = driver.findElement(By.xpath("//*[@id=\"parsley-id-21\"]/li")).getText();
		assertEquals("Please enter a valid email address", actualError);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

		driver.quit();
	}

}
