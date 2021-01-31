package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SearchTest {
	static WebDriver driver;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path+ "\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.dunnesstores.com/");
		driver.manage().window().maximize();
	}

	


	@Test
	void test() {
	
		driver.findElement(By.id("keywords")).sendKeys("Arlo Lamp");
		driver.findElement(By.className("search-form")).submit();
		
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='onetrust-accept-btn-handler']"))).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath("//ol[@class='product-list product-list-main']/li//a"))).click();
		new WebDriverWait(driver, 10).until(webDriver -> driver.getCurrentUrl().equals("https://www.dunnesstores.com/p/arlo-lamp/7761102?colour=Cream"));
		String url = driver.getCurrentUrl();
		
		assertEquals("https://www.dunnesstores.com/p/arlo-lamp/7761102?colour=Cream", url);
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

}
