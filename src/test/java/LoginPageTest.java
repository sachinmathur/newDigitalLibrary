package test.java;

import java.util.concurrent.TimeUnit;

import sac.dl.base.TestBaseSetUp;
import sac.dl.pageObject.CompleteLibraryPage;
import sac.dl.pageObject.LoginPage;
import sac.dl.pageObject.SearchAndBrowseLibraryPage;
import sac.dl.utility.PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value=sac.dl.utility.CustomReportGenerator.class)
public class LoginPageTest extends TestBaseSetUp
{
	private WebDriver driver;
	private LoginPage loginPage;
	private CompleteLibraryPage compLibPage;
	private SearchAndBrowseLibraryPage searchAndBrowseLibraryPage;
	
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
		this.loginPage = PageFactory.initElements(driver, LoginPage.class);
		return;
	}
	
	@Test(groups="Login", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testverifyUIOfLoginPage(String signInText, String textInUserNameTextBox, String textInPasswordTextBox, String forgetUserNamePasswordText, String dontHaveAnAccountText, String purchaseLibraryText, String registerNowText, String searchAndBrowseLibrary)
	{
		log.info("Executing testverifyUIOfLoginPage");
		this.loginPagePageFactory();
		this.loginPage = this.loginPage.verifyUIOfLoginPage(signInText, textInUserNameTextBox, textInPasswordTextBox, forgetUserNamePasswordText, dontHaveAnAccountText, purchaseLibraryText, registerNowText, searchAndBrowseLibrary);
		
		Assert.assertTrue(driver.getPageSource().contains(signInText));
		Assert.assertTrue(PageElements.LoginPageElements.userNameTextBox(driver).isDisplayed());
		Assert.assertTrue(PageElements.LoginPageElements.userNameTextBox(driver).isEnabled());
		Assert.assertTrue(PageElements.LoginPageElements.userNameTextBox(driver).getAttribute("placeholder").equals(textInUserNameTextBox));
		Assert.assertTrue(PageElements.LoginPageElements.passwordTextBox(driver).isDisplayed());
		Assert.assertTrue(PageElements.LoginPageElements.passwordTextBox(driver).isEnabled());
		Assert.assertTrue(PageElements.LoginPageElements.passwordTextBox(driver).getAttribute("placeholder").equals(textInPasswordTextBox));
		Assert.assertTrue(PageElements.LoginPageElements.forgetUserNamePasswordLink(driver).getText().equals(forgetUserNamePasswordText));
		Assert.assertTrue(PageElements.LoginPageElements.dontHaveAccountLabel(driver).getText().equals(dontHaveAnAccountText));
		Assert.assertTrue(PageElements.LoginPageElements.purchaseLibrary(driver).getText().equals(purchaseLibraryText));
		Assert.assertTrue(PageElements.LoginPageElements.registerNow(driver).getText().equals(registerNowText));
		Assert.assertTrue(PageElements.LoginPageElements.searchAndBrowseLibraryLink(driver).getText().equals(searchAndBrowseLibrary));
	}
	
	@Test(groups="Login", dependsOnMethods="testverifyUIOfLoginPage")
	public void testForgetUserNamePasswordLink()
	{
		log.info("Clicking forget username / password link");
		this.loginPagePageFactory();
		loginPage.clickForgetUserNamePasswordLink();
	//	Assert.assertTrue(loginPage.clickForgetUserNamePasswordLink());
	}
	
	@Test(groups="Login", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", dependsOnMethods="testForgetUserNamePasswordLink", alwaysRun=true)
	public void testLogin(String Username, String Password)
	{
		log.info("Executing testLogin");
		this.loginPagePageFactory();
		this.compLibPage = loginPage.login(Username, Password);
		
		String userName = PageElements.CompleteLibraryPageElements.userName(driver).getText();
		Assert.assertEquals(userName, "Dilp Kumar");
	}
	
	@Test(groups="LoginPage", dependsOnGroups="SignOut", alwaysRun=true)
	public void testclickSearchAndBrowseLibraryLink()
	{
		log.info("Clicking Search and Browse Library Link");
		this.loginPagePageFactory();
		this.searchAndBrowseLibraryPage = loginPage.clickSearchAndBrowseLibraryLink();
	}
}