package mt.siteportal.pages.Administration;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GeneralTab {

	@FindBy(how = How.XPATH, using = "//a[@title='Save']")
	public WebElement Save;

	@FindBy(how = How.XPATH, using = "//a[@title='Cancel']")
	public WebElement Cancel;
	
	public abstract Map<String, String> getDetails();
	public abstract Map<String, WebElement> addEditDetails();
}
