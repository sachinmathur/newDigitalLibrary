package sac.dl.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sac.dl.utility.PageElements;

public class SearchResultsPage {

	final WebDriver driver;
	private Logger log;
	
	public SearchResultsPage(WebDriver driver)
	{
		this.driver=driver;
		this.log = Logger.getLogger("applicationLogger");
	}
	
	public SavedSearchesPage verifyAlleTextPagesStack()
	{
		this.waitForElement();
		
		PageElements.SearchResultsPageElements.searchByContent_alleTextPagesStack(driver).click();
		log.info("All eText Pages stack is clicked.");
		return PageFactory.initElements(driver, SavedSearchesPage.class);
	}
	
	public SavedSearchesPage verifyAlleTextStack()
	{
		this.waitForElement();
		PageElements.SearchResultsPageElements.searchByeText_alleTextStack(driver).click();
		log.info("All eText stack is clicked.");
		return PageFactory.initElements(driver, SavedSearchesPage.class);
	}
	
	public SavedSearchesPage verifyAlleTextNotesStack()
	{
		this.waitForElement();
		PageElements.SearchResultsPageElements.searchByNotes_alleTextNotesStack(driver).click();
		log.info("All eText Notes stack is clicked.");
		return PageFactory.initElements(driver, SavedSearchesPage.class);
	}
	
	public void waitForElement()
	{
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(PageElements.SearchResultsPageElements.saveThisSearchButton(driver)));
		return;
	}
}