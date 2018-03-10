package SeleniumTest1;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import SeleniumTest1.ExcelData;
import SeleniumTest1.SEARCH_PG;

public class junitTest {

	/********* constant declaration ********/
	
	/* chrome web driver location */
	public static final String chromeDriver = "C:/drivers/chrome/chromedriver.exe";
	
	/* target URL */
	public static final String targetURL = "http://www.xxx.com";
	
	/* test data */
	public static final String testDataFilePath = "C:/data/TestData.xlsx";
	
	/* test data sheet name */
	public static final String testDataSheet = "SearchBoxTest";
	
	/**************************************/

	public WebDriver driver;
	public SEARCH_PG s;
	
	
	@Before
	public void setUp() throws Exception {
		try
		{
			/* set value of chrome driver key to the driver location*/
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			
			/* instantiate chrome driver object */
			driver = new ChromeDriver();
			
			/* initialize page elements in the Page Object class using PageFactory */
			s = PageFactory.initElements(driver, SEARCH_PG.class);	

			/* maximize the window */
			driver.manage().window().maximize();
			
			/* navigate to the SLI Systems home page */
			driver.get(targetURL);	

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@After
	public void tearDown() throws Exception {
		try
		{
			/* close the window and garbage collect the window instance */ 
			driver.close();
			
		}		
		catch (Exception e)
		{
			System.out.println(e);
		}
	}


	@Test /* positive test */
	public void positiveTest() {
					
		ExcelData excel = new ExcelData();
		String input = null;
		String output = null;
			
		try  /* read input data from excel file */
		{
			excel.readInputData(testDataFilePath, testDataSheet, "positive");
			input = excel.getInputData();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
			
		try
		{  /* read output data from excel file */
			excel.readOutputData(testDataFilePath, testDataSheet, "positive");
			output = excel.getOutputData();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		/* find the search box, enter "red" and hit enter key */
		s.txtboxSearch.sendKeys(input);
		s.txtboxSearch.sendKeys(Keys.RETURN);			
				
		/* get the body text of the search result page */
		String bodyText = driver.findElement(By.tagName("body")).getText();
				
		/* if the body text contains the target word, the test passed */		
		Assert.assertTrue("PASS", bodyText.contains(output));  
	}
	
	
	@Test /* negative test */
	public void negativeTest() {
					
		ExcelData excel = new ExcelData();
		String input = null;
		String output = null;
		
		try  /* read input data from excel file */
		{
			excel.readInputData(testDataFilePath, testDataSheet, "negative");
			input = excel.getInputData();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
		try  /* read output data from excel file */
		{  
			excel.readOutputData(testDataFilePath, testDataSheet, "negative");
			output = excel.getOutputData();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
				
		/* find the search box, enter "red" and hit enter key */
		s.txtboxSearch.sendKeys(input);
		s.txtboxSearch.sendKeys(Keys.RETURN);			
				
		/* get the body text of the search result page */
		String bodyText = driver.findElement(By.tagName("body")).getText();
				
		/* if the body text DOES NOT contain the target word, the test passed */
		Assert.assertTrue("PASS", !(bodyText.contains(output)));		
	}

}
