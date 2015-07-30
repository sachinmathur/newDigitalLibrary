package sac.dl.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sac.dl.utility.PageElements;

public class SavedSearchesPage {

	final WebDriver driver;
	private Logger log;
	
	String xPathOfTerm;

	public SavedSearchesPage(WebDriver driver)
	{
		this.driver=driver;
		log = Logger.getLogger("applicationLogger");
	}
	
	public SearchResultsPage navigateToSavedSearchesPages()
	{
		PageElements.SavedSearchesPageElements.libraryViewOptionsdropdownArrow(driver).click();
		log.info("Library View Dropdown clicked.");
		PageElements.SavedSearchesPageElements.savedSearchesOption(driver).click();
		log.info("\"Saved Searches\" option clicked.");
		this.waitForElementToBeClickable("Edit");
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}
	
	
	public SearchResultsPage clickOnSavedSearchTerm(String searchType, String termToBeClicked)
	{	
		switch(searchType)
		{
			case "Content":
				xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfContentTerm(driver).replace("TERM", termToBeClicked);
				break;
			
			case "eText":
				xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfeTextTerm(driver).replace("TERM", termToBeClicked);
				break;
				
			case "Notes":
				xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfNoteTerm(driver).replace("TERM", termToBeClicked);
		}
		
		WebElement contentTerm = driver.findElement(By.xpath(xPathOfTerm));
		contentTerm.click();
		log.info("Search term- "+termToBeClicked+" clicked");
		this.waitForElementToBeClickable("Save This Search");
		this.navigateToSavedSearchesPages();
		log.info("Navigating back to Saved Searches Page.");
		this.waitForElementToBeClickable("Edit");
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}
	
	public void deleteSavedSearchTerm(String searchType, String termToBeDeleted)
	{
		switch(searchType)
		{
		case "Content":
			xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfContentTerm(driver).replace("TERM", termToBeDeleted).concat("/preceding-sibling::button[@class='btn']");
			break;

		case "eText":
			xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfeTextTerm(driver).replace("TERM", termToBeDeleted).concat("/preceding-sibling::button[@class='btn']");
			break;

		case "Notes":
			xPathOfTerm = PageElements.SavedSearchesPageElements.xPathOfNoteTerm(driver).replace("TERM", termToBeDeleted).concat("/preceding-sibling::button[@class='btn']");
			break;
		}

		PageElements.SavedSearchesPageElements.editButton(driver).click();
		log.info("Edit button clicked.");
		WebElement deleteButton = driver.findElement(By.xpath(xPathOfTerm));
		deleteButton.click();
		log.info("Delete button clicked.");
		this.waitForElementToBeClickable("Confirmation Pop");
		PageElements.SavedSearchesPageElements.yesButtonOnConfirmationPop(driver).click();
		log.info("Clicked \"Yes\" button in confirmation pop.");
		this.waitForElementToBeClickable("Done");
		log.info("Term- "+termToBeDeleted+" deleted");
		PageElements.SavedSearchesPageElements.doneButton(driver).click();
	}
	
	public void waitForElementToBeClickable(String elementName)
	{
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		
		switch(elementName)
		{
			case "Done":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='modal-body']")));
			break;
			
			case "Edit":
				wait.until(ExpectedConditions.elementToBeClickable(PageElements.SavedSearchesPageElements.editButton(driver)));
				break;
				
			case "Save This Search":
				wait.until(ExpectedConditions.elementToBeClickable(PageElements.SavedSearchesPageElements.saveThisSearchButton(driver)));
				break;
				
			case "Confirmation Pop":
				wait.until(ExpectedConditions.elementToBeClickable(PageElements.SavedSearchesPageElements.confirmationPopUp(driver)));
				break;
		}
		return;
	}
}