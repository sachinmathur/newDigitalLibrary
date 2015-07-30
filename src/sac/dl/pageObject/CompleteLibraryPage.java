package sac.dl.pageObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sac.dl.utility.PageElements;

public class CompleteLibraryPage {

	final WebDriver driver;
	private Logger log;
	
	public CompleteLibraryPage(WebDriver driver)
	{
		this.driver=driver;
		this.log= Logger.getLogger("applicationLogger");
	}
	
	public LoginPage signOut()
	{
		PageElements.CompleteLibraryPageElements.signOutButton(driver).click();
		return PageFactory.initElements(driver, LoginPage.class);
	}
	
	public SearchResultsPage performSearch(String searchBy, String searchTerm)
	{
		PageElements.CompleteLibraryPageElements.searchDropdownArrow(driver).click();
		log.info("Search Dropdown Arror clicked.");
		
		switch(searchBy)
		{
		case "Search by Content":
			PageElements.CompleteLibraryPageElements.searchByContentOption(driver).click();
			log.info("\"Search by Content\" option is selected.");
			break;
		case "Search by eText":
			PageElements.CompleteLibraryPageElements.searchByeTextOption(driver).click();
			log.info("\"Search by eText\" option is selected.");
			break;
		case "Search My Notes":
			PageElements.CompleteLibraryPageElements.searchMyNotesOption(driver).click();
			log.info("\"Search My Notes\" option is selected.");
			break;
		}
		
		PageElements.CompleteLibraryPageElements.searchTextBox(driver).clear();
		log.info("Cleared search textbox.");
		PageElements.CompleteLibraryPageElements.searchTextBox(driver).sendKeys(searchTerm);
		log.info("Entering search term- "+ searchTerm);
		PageElements.CompleteLibraryPageElements.searchButton(driver).click();
		log.info("Getting results...");
		this.waitForElement();
		PageElements.CompleteLibraryPageElements.saveThisSearchButton(driver).click();
		log.info("Saving the search results.");
		
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);

		switch(searchBy)
		{
		case "Search by Content":
			wait.until(ExpectedConditions.elementToBeClickable(PageElements.CompleteLibraryPageElements.alleTextPagesStack(driver)));
			break;
			
		case "Search by eText":
			wait.until(ExpectedConditions.elementToBeClickable(PageElements.CompleteLibraryPageElements.alleTextStack(driver)));
			break;
			
		case "Search My Notes":
			wait.until(ExpectedConditions.elementToBeClickable(PageElements.CompleteLibraryPageElements.alleTextNotesStack(driver)));
			break;
		}
		
		return PageFactory.initElements(driver, SearchResultsPage.class);
	}
	
	public boolean verifyBreadCrumbMessage(String searchBy, String searchTerm)
	{
		boolean result = false;
		String initialText = "";
		String getBreadCrumbMessage = PageElements.CompleteLibraryPageElements.breadCrumbMessage(driver).getText();
		
		if(searchBy.equals("Content"))
		{
			initialText = "Content Search: ";
		}
		
		else if(searchBy.equals("eText"))
		{
			initialText = "Book Search: ";
		}
		
		else if(searchBy.equals("Notes"))
		{
			initialText = "Search My Notes: ";
		}
		
		String expectedBreadCrumbMessage = initialText + searchTerm;
		if(getBreadCrumbMessage.equals(expectedBreadCrumbMessage))
		{
			result = true;
		}
		
		else
		{
			result = false;
		}
		
		return result;
	}
	
	public boolean verifyButtonsOnSegmentControl(String expectedButtonsOnSegmentControl)
	{
		boolean result= false;
		List<WebElement> getButtonsOnSegmentControl = PageElements.CompleteLibraryPageElements.buttonsOnSegmentControl(driver);
		Iterator<WebElement> dropdownIterator = getButtonsOnSegmentControl.iterator();
		String[] expectedSegmentControlButtons = expectedButtonsOnSegmentControl.split("\\s*,\\s*");
		int numberOfExpectedSegmentControlButton = expectedSegmentControlButtons.length;
		
		for(int index=0; index<numberOfExpectedSegmentControlButton;index++)
		{
			String buttonOnSegmentControl = dropdownIterator.next().getText();
			String expectedButton = expectedSegmentControlButtons[index];
			
			if(buttonOnSegmentControl.equals(expectedButton))
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
	
	public boolean verifyOptionsInSortByDropdown()
	{
		boolean result = false;
		
		PageElements.CompleteLibraryPageElements.arrowSortByDropdown(driver).click();
		List <WebElement> getOptions = PageElements.CompleteLibraryPageElements.optionsSortByDropdown(driver);
		Iterator<WebElement> optionsIterator = getOptions.iterator();
		
		String[] expectedDropdownOptions = {"Title", "Author"};

		int numberOfDropdownOptions = expectedDropdownOptions.length;
		for (int index=0; index<numberOfDropdownOptions;index++)
		{
			String optionFromDropdown= optionsIterator.next().getText();
			String expectedOption= expectedDropdownOptions[index];
			
			if(optionFromDropdown.equals(expectedOption))
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
		
	public boolean compareStacks(String searchBy, String expectedStacks, String numberOfItemsInEachStack)
	{
		boolean stacksMatched = false;
		List<WebElement> stacks = new LinkedList<WebElement>();
		List<WebElement> numberOfItemsInStacks = new LinkedList<WebElement>();
		String resultStack = "";
		String itemsInStack = "";
		
		if(searchBy.equals("Search by eText"))
		{
			stacks = PageElements.CompleteLibraryPageElements.stacksShownOneTextSearch(driver);
			numberOfItemsInStacks = PageElements.CompleteLibraryPageElements.booksIneTextStacks(driver);
		}
		
		else if(searchBy.equals("Search by Content"))
		{
			stacks = PageElements.CompleteLibraryPageElements.stacksShownOnContentSearch(driver);
			numberOfItemsInStacks = PageElements.CompleteLibraryPageElements.booksInContentStacks(driver);
		}
		
		else if(searchBy.equals("Search by Notes"))
		{
			stacks = PageElements.CompleteLibraryPageElements.stacksShownOneNotesSearch(driver);
			numberOfItemsInStacks = PageElements.CompleteLibraryPageElements.itemsInNotesStacks(driver);
		}
		
		String[] stacksName = expectedStacks.split("\\s*,\\s*");
		String[] eTextInStack = numberOfItemsInEachStack.split("\\s*,\\s*");

		int numberOfExpectedStacks = stacksName.length;
		
		Iterator<WebElement> stackIterator = stacks.iterator();
		Iterator<WebElement> itemsInStackIterator = numberOfItemsInStacks.iterator();

		for(int index=0;index<numberOfExpectedStacks;index++)
		{
			if(searchBy.equals("Search by eText"))
			{
				resultStack = stackIterator.next().getText();
				itemsInStack = itemsInStackIterator.next().getText();
			}
			
			else if(searchBy.equals("Search by Content"))
			{
				resultStack = stackIterator.next().getAttribute("alt");
				itemsInStack = itemsInStackIterator.next().getText();
			}
			
			else if(searchBy.equals("Search by Notes"))
			{
				resultStack = stackIterator.next().getAttribute("title");
				itemsInStack = itemsInStackIterator.next().getText();
			}
			
			if(resultStack.equals(stacksName[index]) && itemsInStack.equals(eTextInStack[index]))
			{	
				stacksMatched = true;
			}

			else
			{
				stacksMatched = false;
			}

		}

		return stacksMatched;
	}
	
	public void waitForElement()
	{
		WebDriverWait wait;
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(PageElements.CompleteLibraryPageElements.saveThisSearchButton(driver)));
		return;
	}
}