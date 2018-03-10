package SeleniumTest1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


/* Page Object class for search page */
public class SEARCH_PG{
 
	final WebDriver driver;
 
	/* the class has a search text box attribute which can be located by NAME */
	@FindBy(how = How.NAME, using = "w")
	public WebElement txtboxSearch;
 
    /* class constructor */
	public SEARCH_PG(WebDriver driver){ 
		this.driver = driver; 
    } 
 
}
