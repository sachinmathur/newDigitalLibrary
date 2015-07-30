package sac.dl.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import sac.dl.utility.PageElements;

public class SignOutPage {
	
	final WebDriver driver;
	private Logger log;

	public SignOutPage(WebDriver driver)
	{
		this.driver=driver;
		this.log = Logger.getLogger("applicationLogger");
	}
	
	public LoginPage clickSignOutLink()
	{
		PageElements.SignOutPageElements.signOutLink(driver).click();
		log.info("Signing out from the application");
		return PageFactory.initElements(driver, LoginPage.class);
	}
}