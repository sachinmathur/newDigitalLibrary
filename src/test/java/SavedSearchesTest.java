package test.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sac.dl.base.TestBaseSetUp;
import sac.dl.pageObject.SavedSearchesPage;

public class SavedSearchesTest extends TestBaseSetUp {

	private WebDriver driver;
	private SavedSearchesPage savedSearchesPage;

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
	
	public void saveSearchPagePageFactory()
	{
		this.savedSearchesPage = PageFactory.initElements(driver, SavedSearchesPage.class);
		return;
	}
	
	@Test(groups="NavigateToSavedSearchesPage", dependsOnGroups="ClickOnAlleTextNotesStack", alwaysRun=true)
	public void testNavigateToSavedSearchesPage()
	{
		log.info("Executing testNavigateToSavedSearchesPage");
		this.saveSearchPagePageFactory();
		savedSearchesPage.navigateToSavedSearchesPages();
	}
	
	@Test(groups="ClickOnSavedSearchTerm", dependsOnMethods="testNavigateToSavedSearchesPage", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testClickOnSavedContentSearchTerm(String termToBeClicked, String expectedResult)
	{
		log.info("Executing testClickOnSavedContentSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.clickOnSavedSearchTerm("Content", termToBeClicked);
	}
	
	@Test(groups="ClickOnSavedSearchTerm", dependsOnMethods="testClickOnSavedContentSearchTerm", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testClickOnSavedeTextSearchTerm(String termToBeClicked, String expectedResult)
	{
		log.info("Executing testClickOnSavedeTextSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.clickOnSavedSearchTerm("eText", termToBeClicked);
	}
	
	@Test(groups="ClickOnSavedSearchTerm", dependsOnMethods="testClickOnSavedeTextSearchTerm", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testClickOnSavedNoteSearchTerm(String termToBeClicked, String expectedResult)
	{
		log.info("Executing testClickOnSavedNoteSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.clickOnSavedSearchTerm("Notes", termToBeClicked);
	}
	
	@Test(groups="DeleteSavedSearchTerm", dependsOnMethods="testClickOnSavedNoteSearchTerm", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testDeleteSavedContentSearchTerm(String termToBeDeleted)
	{
		log.info("Executing testDeleteSavedContentSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.deleteSavedSearchTerm("Content", termToBeDeleted);
	}
	
	@Test(groups="DeleteSavedSearchTerm", dependsOnMethods="testDeleteSavedContentSearchTerm", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testDeleteSavedeTextSearchTerm(String termToBeDeleted)
	{
		log.info("Executing testDeleteSavedeTextSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.deleteSavedSearchTerm("eText", termToBeDeleted);
	}
	
	@Test(groups="DeleteSavedSearchTerm", dependsOnMethods="testDeleteSavedeTextSearchTerm", dataProviderClass= sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testDeleteSavedNoteSearchTerm(String termToBeDeleted)
	{
		log.info("Executing testDeleteSavedNoteSearchTerm");
		this.saveSearchPagePageFactory();
		savedSearchesPage.deleteSavedSearchTerm("Notes", termToBeDeleted);
	}
}