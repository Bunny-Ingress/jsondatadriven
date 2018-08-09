package jsondatadriven;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.parser.ParserException;

public class jsondata {
	
	WebDriver driver;
	String url,searchWord, linkText;
	JSONParser parser = new JSONParser();
	
	
@BeforeTest
	public void setup() throws FileNotFoundException, IOException, ParserException, ParseException
	{
		Object obj = parser.parse(new FileReader("C:\\Users\\Prabhani\\eclipse-workspace\\jsondatadriven\\Data\\data.json"));
	
		JSONObject jsonObject = (JSONObject) obj;
		url = (String) jsonObject.get("URL");
		searchWord = (String) jsonObject.get("SearchWord");
		linkText = (String) jsonObject.get("linkSearch");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Prabhani\\Downloads\\Selinium\\chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
	}
	

@Test
public void testSearch()
{
	driver.findElement(By.id("lst-ib")).sendKeys(searchWord);
	driver.findElement(By.id("hplogo")).click();
	driver.findElement(By.name("btnK")).click();
	driver.findElement(By.linkText(linkText)).click();
	
	String webTitle = driver.getTitle();
	
	Assert.assertEquals(webTitle, linkText);
}

@AfterTest
public void tearDown()
{
	driver.close();
	driver.quit();
}

}

