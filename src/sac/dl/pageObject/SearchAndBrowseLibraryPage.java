package sac.dl.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sac.dl.utility.PageElements;

public class SearchAndBrowseLibraryPage {
	
	final WebDriver driver;
	private Logger log;
	
	public SearchAndBrowseLibraryPage(WebDriver driver)
	{
		this.driver=driver;
		this.log= Logger.getLogger("applicationLogger");
	}
	
	public boolean verifyOptionsInDropdown(String expectedDropdownOptions)
	{	
		boolean result = false;
		
		PageElements.SearchAndBrowseLibraryPageElements.arrowButton(driver).click();
		List<WebElement> listOfLibraryDropdownOptions = PageElements.SearchAndBrowseLibraryPageElements.dropdownOptions(driver);
		Iterator<WebElement> dropdownIterator = listOfLibraryDropdownOptions.iterator();

		String[] expectedOptions = expectedDropdownOptions.split("\\s*,\\s*");
		
		int numberOfExpectedDropdownOptions = expectedOptions.length;
		
		for(int index = 0; index<numberOfExpectedDropdownOptions;index++)
		{
			String elementFromLibraryDropdownList = dropdownIterator.next().getText();
			String elementFromExpectedList = expectedOptions[index];
			
			if(elementFromLibraryDropdownList.equals(elementFromExpectedList))
			{
				result = true;
			}
			
			else
			{
				result = false;
			}
		}
		
		return result;
	}
	
	public boolean verifyBooksPresentInAlleTextStack(String expectedBookNames)
	{
		boolean result = false;
		
		PageElements.SearchAndBrowseLibraryPageElements.AlleTextStack(driver).click();
		List<WebElement> listOfBooksPresentInAlleTextStack = PageElements.SearchAndBrowseLibraryPageElements.listOfBooksPresentInAlleTextStack(driver);
		Iterator<WebElement> booksIterator = listOfBooksPresentInAlleTextStack.iterator();
		
		String[] expectedBooks = expectedBookNames.split("\\s*,\\s*");
		
		int numberOfBooks = listOfBooksPresentInAlleTextStack.size();
		
		for(int index=0;index<numberOfBooks;index++)
		{
			String booksFromAlleTextStack = booksIterator.next().getText();
			String booksFromExpectedList = expectedBooks[index];
			
			if(booksFromAlleTextStack.equals(booksFromExpectedList))
			{
				result = true;
			}
			
			else
			{
				result = false;
			}
		}
		
		return result;
	}
	
	public void openMoreInfoPopUpForUnknownUser(String expectedBookTitle)
	{	
		String xPathOfMoreInfoButton = PageElements.SearchAndBrowseLibraryPageElements.moreInfoButtonOfBook(driver).replace("BOOKNAME", expectedBookTitle);
		WebElement moreInfoButton = driver.findElement(By.xpath(xPathOfMoreInfoButton));
		moreInfoButton.click();
		
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageElements.SearchAndBrowseLibraryPageElements.xPathOfModelWindow(driver))));
		
	//	PageElements.SearchAndBrowseLibraryPageElements.closeButtonOfModelWindow(driver).click();
	}
	
	public boolean verifyOptionsInPreviewChapterDropdown(String expectedPreviewDropdownOptions)
	{
		boolean result = false;
		
		PageElements.SearchAndBrowseLibraryPageElements.arrowPreviewChapterDropdown(driver).click();

		List<WebElement> optionsInPreviewChaptersDropdown = PageElements.SearchAndBrowseLibraryPageElements.previewChaptersDropdownOptions(driver);
		Iterator<WebElement> optionsIterator = optionsInPreviewChaptersDropdown.iterator();

		String[] expectedDropdownOptions = expectedPreviewDropdownOptions.split("\\s*,\\s*");

		int numberOfOptions = optionsInPreviewChaptersDropdown.size();

		for (int index =0; index<numberOfOptions; index++)
		{
			String dropdownOption = optionsIterator.next().getText().trim();
			String expectedOption = expectedDropdownOptions[index];
			if(dropdownOption.equals(expectedOption))
			{
				result = true;
			}
			
			else
			{
				result = false;
			}
		}
		
		return result;
	}
	
	public void launchChapterPreview(String nameOfChapterToBePreviewed)
	{
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		
		List<WebElement> optionsInPreviewChaptersDropdown = PageElements.SearchAndBrowseLibraryPageElements.previewChaptersDropdownOptions(driver);
		Iterator<WebElement> optionsIterator = optionsInPreviewChaptersDropdown.iterator();

		int numberOfOptions = optionsInPreviewChaptersDropdown.size();

		for (int index =0; index<numberOfOptions; index++)
		{
			String dropdownOption = optionsIterator.next().getText().trim();
			if(dropdownOption.equals(nameOfChapterToBePreviewed))
			{	
				String xPathOfChapterEntry = PageElements.SearchAndBrowseLibraryPageElements.xPathOfChapterEntryToBeClickedInPreviewChapterDropdown(driver).replace("CHAPTER_NAME", nameOfChapterToBePreviewed);
				driver.findElement(By.xpath(xPathOfChapterEntry)).click();
			}
			
			else
			{
				log.error("Chapter entry not found in dropdown.");
			}
		}
		
		PageElements.SearchAndBrowseLibraryPageElements.goButton(driver).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PageElements.SearchAndBrowseLibraryPageElements.chapterPreviewModel(driver))));
	}
}