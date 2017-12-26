package mt.siteportal.pages.studyDashboard;

import mt.siteportal.pages.BasePage;
import mt.siteportal.utils.Browser;
import mt.siteportal.utils.helpers.UiHelper;
import mt.siteportal.utils.tools.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EsignDialog extends BasePage {
	 
	public EsignDialog(WebDriver driver) {
		super(driver);
	}
	
	// using = "loginName" - this is OK till that ID is only needed here, at 1 place.

	private By eSignDialogueBox = new By.ByCssSelector("div#auditTrailConfirmation div.modal-content");
//	private By login = new By.ById("loginInput");
//	private By pass = new By.ById("passwordInput");
	private By login = new By.ByCssSelector("div#auditTrailConfirmation input#loginInput");
	private By pass = new By.ByCssSelector("div#auditTrailConfirmation input#passwordInput");
	private By eye = new By.ByCssSelector("div#auditTrailConfirmation i.icon-eye-password");
	private By closeIcon = new By.ByCssSelector("div#auditTrailConfirmation button.close");
	
	
//	private By ok = new By.ByXPath("//div[text()='OK']");
	private By ok = new By.ByXPath("//div[@data-ng-click='okClicked()']");
//	private By cancel = new By.ByXPath("//div[@class='modal-content']//div[text()='Cancel']");
	private By cancel = new By.ByXPath("//div[@data-ng-click='cancelClicked()']");
	private By errorMessage = new By.ByXPath("//label[text()='Please check username and password.']");
	private By reasonDropdown = new By.ByXPath("//div[@data-ng-change='okValidation()']//button");
	private By reasonComments = new By.ByXPath("//textarea[@data-ng-change='okValidation()']");
	    
	/**
	 * Types in loginName and password and submits the login form.
	 * 
	 * @param loginName
	 *            - name to use for login.
	 * @param password
	 *            - password to use.
	 * @return - HomePage PageObject.
	 */
	    
	public void loginAs(String loginName, String password) {
		Log.logInfo(String.format("Confirming as %s/%s", loginName, password));
		UiHelper.clear(login);
		UiHelper.sendKeys(login, loginName);
		UiHelper.clear(pass);
		UiHelper.sendKeys(pass, password);
		this.esignDialogEyeCharactersCheck(loginName, password);
		UiHelper.click(ok);
	}
	    
	public void inputPredefinedReason(String dropDownvalue) {
		if (!dropDownvalue.equalsIgnoreCase("Others")) {
			UiHelper.selectInDropdownBtn(reasonDropdown, dropDownvalue);
		} else {
			Log.logError("Dropdown value shouldn't be 'Others'");
		}
	}

	public void inputOthersReason(String comment) {
			UiHelper.selectInDropdownBtn(reasonDropdown, "Others");
			UiHelper.sendKeys(reasonComments, comment);
	}
	
	/**
	 * Checks if the esign dialog is opened on the page Verifies the fileds and
	 * buttons
	 *
	 * @return - True if opened otherwise False
	 */
	public boolean isDialogOpened() {
		Log.logInfo("Verifies the e-Sign dialog is opened");
		if (UiHelper.isPresent(eSignDialogueBox))
			return true;
		Log.logInfo("E-Sign dialogue is not opened...");
		return false;
		
		/*boolean loginName = UiHelper.isPresent(login);
		boolean password = UiHelper.isPresent(pass);
		boolean dropdown = UiHelper.isPresent(reasonDropdown);*/
		/*boolean okButton = UiHelper.isPresent(ok);
		boolean cancelButton = UiHelper.isPresent(cancel);*/
		/*if (loginName && password && dropdown) {
			return true;
		} else {
			Log.logError(
					"loginName: " + loginName + "\n" + "password: " + password + "\n" + "reasonDropdown: " + dropdown);
			return false;
		}*/
	}

	/**
	 * Checks if the error message is displayed in the dialog
	 * Verifies the fileds and buttons
	 *
	 * @return - True if opened otherwise False
	 */
	public boolean isCredentialsValid(String loginName, String password) {
		Log.logInfo("Verifies the valid credentials");
		UiHelper.sendKeys(login, loginName);
		UiHelper.sendKeys(pass, password);
		UiHelper.click(ok);
		return UiHelper.isPresent(errorMessage);
	}

	/**
	 * Cancel the E-signing
	 */
	public void cancelEsign() {
		Log.logInfo("Canceling esign dialog");
		UiHelper.click(cancel);
	}
	
	/**
	 * @author ubiswas
	 * 
	 *         Description - Checking visibility of eye characters in password
	 *         field at e-sign dialog.
	 * @param loginName
	 * @param password
	 */
	public void esignDialogEyeCharactersCheck(String loginName, String password) {
		Log.logStep("Click on Eye icon");
		UiHelper.click(eye);
		UiHelper.sleep(5000);
		String enteredText = UiHelper.findElement(pass).getAttribute("value").toString();
		if (enteredText.equalsIgnoreCase(password)) {
			Log.logInfo("Eye characters are visible");
		} else {
			Log.logError("Eye characters are not visible");
		}
	}
	
	public void enterPassAndValidateEyeCharacters(String adminLogin, String adminPasword) {
		UiHelper.sleep();
		UiHelper.sendKeys(login, adminLogin);
		UiHelper.sendKeys(pass, adminPasword);
		this.esignDialogEyeCharactersCheck(adminLogin, adminPasword);
		UiHelper.click(closeIcon);
	}
}
