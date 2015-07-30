package test.java;

import java.util.concurrent.TimeUnit;

import sac.dl.base.TestBaseSetUp;
import sac.dl.pageObject.SavedSearchesPage;
import sac.dl.pageObject.SearchResultsPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchResultsTest extends TestBaseSetUp
{
	private WebDriver driver;
	private SearchResultsPage searchPage;
	private SavedSearchesPage mySavedSearches;
	
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
	
	public void searchResultsPagePageFactory()
	{
		this.searchPage = PageFactory.initElements(driver, SearchResultsPage.class);
		return;
	}
	
	@Test(groups = "ClickOnAlleTextPagesStack", dependsOnGroups="PerformSearchingByeContents", alwaysRun=true)
	public void testverifyAlleTextPagesStack()
	{	
		log.info("Executing testverifyAlleTextPagesStack");
		this.searchResultsPagePageFactory();
		searchPage.verifyAlleTextPagesStack();
	}
	
	@Test(groups = "ClickOnAlleTextStack", dependsOnGroups="PerformSearchingByeText", alwaysRun=true)
	public void testverifyAlleTextStack()
	{
		log.info("Executing testverifyAlleTextStack");
		this.searchResultsPagePageFactory();
		searchPage.verifyAlleTextStack();
	}
	
	@Test(groups = "ClickOnAlleTextNotesStack", dependsOnGroups="PerformSearchingByNotes", alwaysRun=true)
	public void testverifyAlleTextNotesStack()
	{
		log.info("Executing testverifyAlleTextNotesStack");
		this.searchResultsPagePageFactory();
		this.mySavedSearches = searchPage.verifyAlleTextNotesStack();
	}
}