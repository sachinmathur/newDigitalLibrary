package sac.dl.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sac.dl.utility.PageElements;

public class LoginPage {

	final WebDriver driver;
	private Logger log;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		this.log = Logger.getLogger("applicationLogger");
	}

	public void enterUserName(String userName)
	{
		PageElements.LoginPageElements.userNameTextBox(driver).clear();
		PageElements.LoginPageElements.userNameTextBox(driver).sendKeys(userName);
		log.info("Username entered.");
	}
	
	public void enterPassword(String password)
	{
		PageElements.LoginPageElements.passwordTextBox(driver).clear();
		PageElements.LoginPageElements.passwordTextBox(driver).sendKeys(password);
		log.info("Password entered");
	}
	
	public void clickLoginButton()
	{
		PageElements.LoginPageElements.loginButton(driver).click();
		log.info("Login button clicked");
	}
	
	public LoginPage verifyUIOfLoginPage(String signInText, String textInUserNameTextBox, String textInPasswordTextBox, String forgetUserNamePasswordText, String dontHaveAnAccountText, String purchaseLibraryText, String registerNowText, String searchAndBrowseLibrary)
	{
		this.waitForElement();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public CompleteLibraryPage login(String userName, String password)
	{
		enterUserName(userName);
		enterPassword(password);
		clickLoginButton();
		log.info("Logging In...");
		
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='headerLinks']/a[@id='signout']")));
		
		log.info("Complete Library page loaded.");
		
		return PageFactory.initElements(driver, CompleteLibraryPage.class);
	}
	
	public SearchAndBrowseLibraryPage clickSearchAndBrowseLibraryLink()
	{
		PageElements.SearchAndBrowseLibraryPageElements.searchAndBrowseLibraryLink(driver).click();
		
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(PageElements.SearchAndBrowseLibraryPageElements.signInLink(driver)));
		return PageFactory.initElements(driver, SearchAndBrowseLibraryPage.class);
	}
	
	public void clickForgetUserNamePasswordLink()
	{	
		PageElements.LoginPageElements.forgetUserNamePasswordLink(driver).click();
	}
	
	public void waitForElement()
	{
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(PageElements.LoginPageElements.userNameTextBox(driver)));
		return;
	}
}