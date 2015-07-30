package test.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sac.dl.base.TestBaseSetUp;
import sac.dl.pageObject.SearchAndBrowseLibraryPage;
import sac.dl.utility.PageElements;

public class SearchAndBrowseLibraryTest extends TestBaseSetUp {
	
	private WebDriver driver;
	private SearchAndBrowseLibraryPage searchAndBrowseLibrary;
	
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
	
	public void searchAndBrowseLibraryPageFactory()
	{
		this.searchAndBrowseLibrary = PageFactory.initElements(driver, SearchAndBrowseLibraryPage.class);
		return;
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnGroups="LoginPage", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyOptionsInDropdown(String dropdownValuesExpected)
	{
		log.info("Opening dropdown");
		this.searchAndBrowseLibraryPageFactory();
		Assert.assertTrue(searchAndBrowseLibrary.verifyOptionsInDropdown(dropdownValuesExpected));
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnMethods="testVerifyOptionsInDropdown", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyBooksPresentInAlleTextStack(String booksNameExpected)
	{
		log.info("Verifying the books present in All eText Stack");
		this.searchAndBrowseLibraryPageFactory();
		Assert.assertTrue(searchAndBrowseLibrary.verifyBooksPresentInAlleTextStack(booksNameExpected));
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnMethods="testVerifyBooksPresentInAlleTextStack", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyUIOfMoreInfoPopUpUnknownUser(String bookTitle, String bookAuthor, String copyrightYear, String bookDescription)
	{
		log.info("Verifying the UI of More Info pop-up for an unknown user");
		this.searchAndBrowseLibraryPageFactory();
		searchAndBrowseLibrary.openMoreInfoPopUpForUnknownUser(bookTitle);
		
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.bookNameOnMoreInfoPopUp(driver), bookTitle);
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.authorNameOnMoreInfoPopUp(driver), bookAuthor);
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.copyrightYeareOnMoreInfoPopUp(driver), copyrightYear);
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.previewChaptersLabel(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.previewChaptersDropdown(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.goButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.closeButtonOfModelWindow(driver).isDisplayed());
		
		String description = PageElements.SearchAndBrowseLibraryPageElements.bookDescriptionOnMoreInfoPopUp(driver);
		String partialDescription = description.substring(0,112);
		Assert.assertTrue(partialDescription.equals(bookDescription));
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnMethods="testVerifyUIOfMoreInfoPopUpUnknownUser", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyOptionsInPreviewChapterDropdown(String expectedOptionsInDropdown)
	{
		log.info("Verifying the options in 'Chapter Preview' download");
		this.searchAndBrowseLibraryPageFactory();
		Assert.assertTrue(searchAndBrowseLibrary.verifyOptionsInPreviewChapterDropdown(expectedOptionsInDropdown));
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnMethods="testVerifyOptionsInPreviewChapterDropdown", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testLaunchChapterPreview(String chapterName)
	{
		log.info("Previewing the given chapter");
		this.searchAndBrowseLibraryPageFactory();
		searchAndBrowseLibrary.launchChapterPreview(chapterName);
	}
	
	@Test(groups="SearchAndBrowseLibrary", dependsOnMethods="testLaunchChapterPreview", dataProviderClass =sac.dl.utility.ProvideTestData.class, dataProvider="ExcelDataProvider", alwaysRun=true)
	public void testVerifyUIOfChapterPreview(String bookName, String authorLastName, String pageInfo)
	{
		log.info("Previewing the given chapter");
		
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.bookNameOnChapterPreview(driver).getText(), bookName);
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.authorLastNameOnChapterPreview(driver).getText(), authorLastName);
		Assert.assertEquals(PageElements.SearchAndBrowseLibraryPageElements.pageNumberOnChapterPreview(driver).getText(), pageInfo);
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.showTwoPageViewButton(driver).isDisplayed());
		Assert.assertFalse(PageElements.SearchAndBrowseLibraryPageElements.showOnePageViewButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.zoomInButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.zoomOutButton(driver).isDisplayed());
		Assert.assertTrue(PageElements.SearchAndBrowseLibraryPageElements.nextPageButton(driver).isDisplayed());
		Assert.assertFalse(PageElements.SearchAndBrowseLibraryPageElements.previousPageButton(driver).isDisplayed());
	}
}