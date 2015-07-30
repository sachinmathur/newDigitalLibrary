package test.java;

import java.util.concurrent.TimeUnit;

import sac.dl.base.TestBaseSetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sac.dl.pageObject.CompleteLibraryPage;
import sac.dl.pageObject.SearchResultsPage;
import sac.dl.utility.PageElements;

public class CompleteLibraryTest extends TestBaseSetUp{

	private WebDriver driver;
	private CompleteLibraryPage compLibPage;
	private SearchResultsPage searchPage;

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
	
	public void completeLibraryPageFactory()
	{
		this.compLibPage = PageFactory.initElements(driver, CompleteLibraryPage.class);
		return;
	}
	
	@Test(groups="CompleteLibrary", dependsOnGroups="Login", dataProviderClass=sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyUIOfCompleteLibraryPage(String countOfBooksInAlleTextStack, String countOfAllMyLabs, String countOfAllVideos, String countOfAllResources)
	{
		log.info("Executing testVerifyUIOfCompleteLibraryPage");
		
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.numberOfBooksInAlleTextStack(driver).getText().equals(countOfBooksInAlleTextStack));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.numberOfAllMyLabs(driver).getText().equals(countOfAllMyLabs));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.numberOfVideosInAllVideosStack(driver).getText().equals(countOfAllVideos));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.numberOfResourcesInAllResourcesStack(driver).getText().equals(countOfAllResources));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.signOutButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.helpLinkOnHeader(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.searchDropdownArrow(driver).isEnabled());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.searchTextBox(driver).isEnabled());
	}
	
	@Test(groups="PerformSearchingByeContents", dependsOnMethods="testVerifyUIOfCompleteLibraryPage", dataProviderClass=sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testSearchByContents(String termToBeSearched, String expectedButtonsOnSegmentControl, String expectedStack, String numberOfPagesInEachStack)
	{
		String searchBy = "Search by Content";
		log.info("Executing testSearchByContents");
		this.completeLibraryPageFactory();
		this.searchPage = compLibPage.performSearch(searchBy, termToBeSearched);
		
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.alleTextPagesStack(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyButtonsOnSegmentControl(expectedButtonsOnSegmentControl));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.saveThisSearchButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.searchGoogleButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.labelSortBy(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.dropdownSortBy(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyOptionsInSortByDropdown());
		Assert.assertTrue(compLibPage.verifyBreadCrumbMessage("Content", termToBeSearched));
		Assert.assertTrue(compLibPage.compareStacks(searchBy, expectedStack, numberOfPagesInEachStack));
	}
	
	@Test(groups="PerformSearchingByeText", dependsOnGroups="ClickOnAlleTextPagesStack", dataProviderClass=sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testSearchByeText(String termToBeSearched, String expectedButtonsOnSegmentControl, String expectedStack, String numberOfeTextInEachStack)
	{
		String searchBy = "Search by eText";
		log.info("Executing testSearchByeText");
		this.completeLibraryPageFactory();
		this.searchPage = compLibPage.performSearch(searchBy, termToBeSearched);
	
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.alleTextStack(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyButtonsOnSegmentControl(expectedButtonsOnSegmentControl));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.saveThisSearchButton(driver).isDisplayed());
		Assert.assertFalse(PageElements.CompleteLibraryPageElements.labelSortBy(driver).isDisplayed());
		Assert.assertFalse(PageElements.CompleteLibraryPageElements.dropdownSortBy(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyBreadCrumbMessage("eText", termToBeSearched));
		Assert.assertTrue(compLibPage.compareStacks(searchBy, expectedStack, numberOfeTextInEachStack));
	}
	
	@Test(groups="PerformSearchingByNotes",dependsOnGroups="ClickOnAlleTextStack", dataProviderClass=sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testSearchMyNotes(String termToBeSearched, String expectedButtonsOnSegmentControl, String expectedStack, String numberOfNotesInEachStack)
	{
		String searchBy = "Search by Notes";
		log.info("Executing testSearchMyNotes");
		this.completeLibraryPageFactory();
		this.searchPage = compLibPage.performSearch("Search My Notes", termToBeSearched);
		
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.alleTextNotesStack(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyButtonsOnSegmentControl(expectedButtonsOnSegmentControl));
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.saveThisSearchButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.labelSortBy(driver).isDisplayed());
		Assert.assertTrue(PageElements.CompleteLibraryPageElements.dropdownSortBy(driver).isDisplayed());
		Assert.assertTrue(compLibPage.verifyOptionsInSortByDropdown());
		Assert.assertTrue(compLibPage.verifyBreadCrumbMessage("Notes", termToBeSearched));
		Assert.assertTrue(compLibPage.compareStacks(searchBy, expectedStack, numberOfNotesInEachStack));
	}
}