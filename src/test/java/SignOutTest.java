package test.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sac.dl.base.TestBaseSetUp;
import sac.dl.pageObject.LoginPage;
import sac.dl.pageObject.SignOutPage;

public class SignOutTest extends TestBaseSetUp {

	private WebDriver driver;
	private LoginPage loginPage;
	private SignOutPage signOut;
	
	@BeforeClass
	public void beforeClass()
	{
		this.driver=setDriver();
		this.log=setLogger();
	}
	
	@BeforeMethod
	public void waitBeforeCallingMethod()
	{
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return;
	}
	
	public void loginPagePageFactory()
	{
		this.signOut = PageFactory.initElements(driver, SignOutPage.class);
		return;
	}
	
	@Test(groups="SignOut", dependsOnGroups="DeleteSavedSearchTerm", alwaysRun=true)
	public void testSignOut()
	{
		log.info("Executing testSignOut");
		this.loginPagePageFactory();
		this.loginPage = signOut.clickSignOutLink();
	}
}