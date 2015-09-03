package sac.dl.utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElements {

	public static class LoginPageElements
	{
		final WebDriver driver;
		
		public LoginPageElements(WebDriver driver)
		{
			this.driver=driver;
		}
		
		public static WebElement userNameTextBox(WebDriver driver)
		{
			WebElement userNameTextBox = driver.findElement(By.cssSelector("#userNameTxt"));
			return userNameTextBox;
		}
		
		public static WebElement passwordTextBox(WebDriver driver)
		{
			WebElement passwordTextBox= driver.findElement(By.cssSelector("#pwdTxt"));
			return passwordTextBox;
		}
		
		public static WebElement loginButton(WebDriver driver)
		{
			WebElement loginButton= driver.findElement(By.id("loginBtnId"));
			return loginButton;
		}
		
		public static WebElement forgetUserNamePasswordLink(WebDriver driver)
		{
			WebElement forgetUserNamePasswordLink = driver.findElement(By.cssSelector(".forgotLink > a"));
			return forgetUserNamePasswordLink;
		}
		
		public static WebElement dontHaveAccountLabel(WebDriver driver)
		{
			WebElement dontHaveAccountLabel = driver.findElement(By.cssSelector(".dontHaveAccountLabel > span"));
			return dontHaveAccountLabel;
		}
		
		public static WebElement purchaseLibrary(WebDriver driver)
		{
			WebElement purchaseLibrary = driver.findElement(By.cssSelector(".searchBrowseLink > a[title='Purchase Library']"));
			return purchaseLibrary;
		}
		
		public static WebElement registerNow(WebDriver driver)
		{
			WebElement registerNow = driver.findElement(By.cssSelector(".registerLink>a[title='Register Now']"));
			return registerNow;
		}
		
		public static WebElement searchAndBrowseLibraryLink(WebDriver driver)
		{
			WebElement searchAndBrowseLibraryLink = driver.findElement(By.cssSelector(".searchBrowseLink>a[title='Search and browse Library']"));
			return searchAndBrowseLibraryLink;
		}
	}
	
	public static class CompleteLibraryPageElements
	{
		final WebDriver driver;
		
		public CompleteLibraryPageElements(WebDriver driver)
		{
			this.driver=driver;
		}
		
		public static WebElement userName(WebDriver driver)
		{
			WebElement userName = driver.findElement(By.cssSelector(".welcome>span[id='username']>i"));
			return userName;
		}
		
		public static List<WebElement> buttonsOnSegmentControl(WebDriver driver)
		{
			List<WebElement> segmentControlButtons = driver.findElements(By.cssSelector("#segmented-controls>div>div"));
			return segmentControlButtons;
		}
		
		public static WebElement searchDropdownArrow(WebDriver driver)
		{
			WebElement searchDropdownArrow = driver.findElement(By.cssSelector(".searchOptionsDropdown>div>button.searchDropBtn"));
			return searchDropdownArrow;
		}
		
		public static WebElement searchByContentOption(WebDriver driver)
		{
		//	WebElement searchByContentOption = driver.findElement(By.cssSelector("ul#searchDropDownOptions>li#searchDrop2>a:contains('Search for Content')"));
			WebElement searchByContentOption = driver.findElement(By.xpath("//ul[@id='searchDropDownOptions']/li[@id='searchDrop2']/a[contains(.,'Search for Content')]"));
			return searchByContentOption;
		}
		
		public static WebElement searchByeTextOption(WebDriver driver)
		{
			WebElement searchByeTextOption = driver.findElement(By.xpath("//ul[@id='searchDropDownOptions']/li[@id='searchDrop1']/a[contains(.,'Search for an eText')]"));
			return searchByeTextOption;
		}
		
		public static WebElement searchMyNotesOption(WebDriver driver)
		{
			WebElement searchMyNotesOption = driver.findElement(By.xpath("//ul[@id='searchDropDownOptions']/li[@id='searchDrop3']/a[contains(.,'Search My Notes')]"));
			return searchMyNotesOption;
		}
		
		public static WebElement searchTextBox(WebDriver driver)
		{
			WebElement searchTextBox = driver.findElement(By.xpath("//li[@id='dropdowmSearch']/div[@id='combodiv']/div[@class='leftAlign']/input[@id='combotextbox']"));
			return searchTextBox;
		}
		
		public static WebElement searchButton(WebDriver driver)
		{
			WebElement searchButton = driver.findElement(By.cssSelector("span.searchBtn>a>img#searchIconImage"));
			return searchButton;
		}
		
		public static WebElement breadCrumbMessage(WebDriver driver)
		{
			WebElement breadCrumb = driver.findElement(By.cssSelector("ul#DLBreadcrumb>li>span"));
			return breadCrumb;
		}
		
		public static WebElement saveThisSearchButton(WebDriver driver)
		{
			WebElement saveThisSearchButton = driver.findElement(By.xpath("//span[@id='saveBtnDivID']/a[contains(.,'Save this Search')]"));
			return saveThisSearchButton;
		}
		
		public static WebElement searchGoogleButton(WebDriver driver)
		{
			WebElement saveThisSearchButton = driver.findElement(By.cssSelector("div#saveBtnDiv>span#searchGoogleID>a"));
			return saveThisSearchButton;
		}
		
		public static List<WebElement> stacksShownOnContentSearch(WebDriver driver)
		{
			List<WebElement> stacks = driver.findElements(By.xpath("//ul[@class='bookShelftable']/li/div[@class='contentstackimagediv ']/a/img"));
			return stacks;
		}
		
		public static List<WebElement> booksInContentStacks(WebDriver driver)
		{
			List<WebElement> booksInContentStacks = driver.findElements(By.xpath("//div[@id='searchContent']/ul/li/div/descendant::span[@class='editionDivSearchBook']"));
			return booksInContentStacks;
		}
		
		public static List<WebElement> stacksShownOneTextSearch(WebDriver driver)
		{
			List<WebElement> stacks = driver.findElements(By.xpath("//div[@id='searchBook']/ul[@class='bookShelftable']/li/div[@class='categorymetadata']/label/span"));
			return stacks;
		}
		
		public static List<WebElement> booksIneTextStacks(WebDriver driver)
		{
			List<WebElement> booksIneTextStacks = driver.findElements(By.xpath("//div[@id='searchBook']/ul/li[@class='searchBookColumn span3']/div[@class='categorymetadata']/span"));
			return booksIneTextStacks;
		}
		
		public static List<WebElement> stacksShownOneNotesSearch(WebDriver driver)
		{
			List<WebElement> stacks = driver.findElements(By.xpath("//div[@id='searchJournalBooks']/ul/li"));
			return stacks;
		}
		
		public static List<WebElement> itemsInNotesStacks(WebDriver driver)
		{
			List<WebElement> booksIneTextStacks = driver.findElements(By.xpath("//div[@id='searchJournalBooks']/ul/li/div/descendant::span[@class='editionDivSearchBook']"));
			return booksIneTextStacks;
		}
		
		public static WebElement signOutButton(WebDriver driver)
		{
			WebElement signOutButton = driver.findElement(By.linkText("Sign Out"));
			return signOutButton;
		}
		
		public static WebElement alleTextPagesStack(WebDriver driver)
		{
			WebElement alleTextPagesStack = driver.findElement(By.xpath("//li[@id='allPagesID']/div[@class='contentstackimagediv ']/a/img[@title='All eText Pages']"));
			return alleTextPagesStack;
		}
		
		public static WebElement alleTextStack(WebDriver driver)
		{
			WebElement alleTextStack = driver.findElement(By.xpath("//div[@id='searchBook']/ul[@class='bookShelftable']/li[@class='searchBookColumn span3']/div[@class='contentstackimagediv']/a/img[@title='All eTexts']"));
			return alleTextStack;
		}
		
		public static WebElement alleTextNotesStack(WebDriver driver)
		{
			WebElement alleTextNotesStack = driver.findElement(By.xpath("//div[@id='searchJournalBooks']/ul[@class='bookShelftable']/li[@id='allPagesID']/div[@class='contentstackimagediv ']/a/img[@title='All eText Notes']"));
			return alleTextNotesStack;
		}
		
		public static WebElement numberOfBooksInAlleTextStack(WebDriver driver)
		{
			WebElement numberOfBooksInAlleTextStack = driver.findElement(By.xpath("//div[@class='categorymetadata']/span[@id='dispCompLibbookCount0']"));
			return numberOfBooksInAlleTextStack;
		}
		
		public static WebElement numberOfAllMyLabs(WebDriver driver)
		{
			WebElement numberOfAllMyLabs = driver.findElement(By.xpath("//div[@class='categorymetadata']/span[@id='bookCount']"));
			return numberOfAllMyLabs;
		}
		
		public static WebElement numberOfVideosInAllVideosStack(WebDriver driver)
		{
			WebElement numberOfVideosInAllVideosStack = driver.findElement(By.xpath("//li[@id='completeLibraryCategoriesAllVideos']/div[@class='categorymetadata assetcategory']/span[@class='editionDivSearchBook']"));
			return numberOfVideosInAllVideosStack;
		}
		
		public static WebElement numberOfResourcesInAllResourcesStack(WebDriver driver)
		{
			WebElement numberOfResourcesInAllResourcesStack = driver.findElement(By.xpath("//li[@id='completeLibraryCategoriesAllResources']/div[@class='categorymetadata assetcategory']/span[@class='editionDivSearchBook']"));
			return numberOfResourcesInAllResourcesStack;
		}
		
		public static WebElement helpLinkOnHeader(WebDriver driver)
		{
			WebElement helpLinkOnHeader = driver.findElement(By.xpath("//div[@class='welcome']/span[@id='help']"));
			return helpLinkOnHeader;
		}
		
		public static WebElement labelSortBy(WebDriver driver)
		{
			WebElement labelSortBy = driver.findElement(By.xpath("//div[@class='pull-right']/ul/li[@class='sortbylabel']"));
			return labelSortBy;
		}
		
		public static WebElement dropdownSortBy(WebDriver driver)
		{
			WebElement dropdownSortBy = driver.findElement(By.xpath("//div[@class='pull-right']/ul/li[@class='sortbydropdown']"));
			return dropdownSortBy;
		}
		
		public static List<WebElement> optionsSortByDropdown(WebDriver driver)
		{
			List<WebElement> options = driver.findElements(By.xpath("//ul[@class='dropdown-menu sortingOptionsdropdown']/li/a"));
			return options;
		}
		
		public static WebElement arrowSortByDropdown(WebDriver driver)
		{
			WebElement arrow = driver.findElement(By.cssSelector("button[id='sortDisplayOptionsBtnID']>div>img"));
			return arrow;
		}
		
		public static WebElement dropdownSortByButton(WebDriver driver)
		{
			WebElement dropdownSortByButton = driver.findElement(By.cssSelector("div.pull-right>ul>li.sortbydropdown>#sortingoptions>#sortDisplayOptions>button"));
			return dropdownSortByButton;
		}
		
		public static WebElement dropdownSortByDefaultSelectedOption(WebDriver driver)
		{
			WebElement dropdownSortByDefaultSelectedOption = driver.findElement(By.cssSelector("div.pull-right>ul>li.sortbydropdown>div#sortingoptions>div#sortDisplayOptions>button>div>div"));
			return dropdownSortByDefaultSelectedOption;
		}
		
		public static WebElement dropdownSortByOption(WebDriver driver)
		{
			WebElement dropdownSortByOption = driver.findElement(By.cssSelector("div.pull-right>ul>li.sortbydropdown>div#sortingoptions>div#sortDisplayOptions>ul"));
			return dropdownSortByOption;
		}
	}
	
	public static class SavedSearchesPageElements
	{
		public static String xPathOfContentTerm(WebDriver driver)
		{
			String xPathOfContentTerm = "//div[@id='searchForContentSavedSearch']/ul/descendant::a[contains(.,'TERM')]";
			return xPathOfContentTerm;
		}
		
		public static String xPathOfeTextTerm(WebDriver driver)
		{
			String xPathOfeTextTerm = "//div[@id='searchForBookSavedSearch']/ul/descendant::a[contains(.,'TERM')]";
			return xPathOfeTextTerm;
		}
		
		public static String xPathOfNoteTerm(WebDriver driver)
		{
			String xPathOfNoteTerm = "//div[@id='personalJournalSavedSearch']/ul/descendant::a[contains(.,'TERM')]";
			return xPathOfNoteTerm;
		}
		
		public static WebElement libraryViewOptionsdropdownArrow(WebDriver driver)
		{
			WebElement libraryViewOptionsdropdownArrow = driver.findElement(By.className("dropDownArrow")); 
			return libraryViewOptionsdropdownArrow;
		}
		
		public static WebElement savedSearchesOption(WebDriver driver)
		{
			WebElement savedSearchesOption = driver.findElement(By.xpath("//li[@class='dropdown open']/ul/descendant::div[contains(.,'  Saved Searches')]"));
			return savedSearchesOption;
		}
		
		public static WebElement editButton(WebDriver driver)
		{
			WebElement editButton = driver.findElement(By.id("editSavedSearchInputBtnID"));
			return editButton;
		}
		
		public static WebElement saveThisSearchButton(WebDriver driver)
		{
			WebElement saveThisSearchButton = driver.findElement(By.xpath("//span[@id='saveBtnDivID']/a[contains(.,'Save this Search')]"));
			return saveThisSearchButton;
		}
		
		public static WebElement confirmationPopUp(WebDriver driver)
		{
			WebElement confirmationPopUp = driver.findElement(By.xpath("//div[@class='modal-body']"));
			return confirmationPopUp;
		}
		
		public static WebElement yesButtonOnConfirmationPop(WebDriver driver)
		{
			WebElement yesButtonOnConfirmationPop = driver.findElement(By.xpath("//div[@class='modal-body']/div[@class='alignRight']/button[contains(.,'Yes')]"));
			return yesButtonOnConfirmationPop;
		}
		
		public static WebElement noButtonOnConfirmationPop(WebDriver driver)
		{
			WebElement noButtonOnConfirmationPop = driver.findElement(By.xpath("//div[@class='modal-body']/div[@class='alignRight']/button[contains(.,'No')]"));
			return noButtonOnConfirmationPop;
		}
		
		public static WebElement doneButton(WebDriver driver)
		{
			WebElement doneButton = driver.findElement(By.xpath("//div[@id='doneSavedSearchBtnID']/input[@title='Done']"));
			return doneButton;	
		}
	}
	
	public static class SearchResultsPageElements
	{
		public static WebElement searchByContent_alleTextPagesStack(WebDriver driver)
		{
			WebElement searchByContent_alleTextPagesStack = driver.findElement(By.cssSelector("div#searchContent>ul.bookShelftable>li#allPagesID>div.contentstackimagediv >a.displaytab>img[alt='All eText Pages']"));
		//	WebElement searchByContent_alleTextPagesStack = driver.findElement(By.xpath("//div[@id='searchContent']/ul[@class='bookShelftable']/li[@id='allPagesID']/div[@class='contentstackimagediv ']/a[@class='displaytab']/img[@alt='All eText Pages']"));
			return searchByContent_alleTextPagesStack;
		}
		
		public static WebElement searchByeText_alleTextStack(WebDriver driver)
		{
			WebElement searchByeText_alleTextStack = driver.findElement(By.cssSelector("div#searchBook>ul.bookShelftable>li[title='All eTexts']>div.contentstackimagediv>a.displaytab>img[title='All eTexts']"));
		//	WebElement searchByeText_alleTextStack = driver.findElement(By.xpath("//div[@id='searchBook']/ul[@class='bookShelftable']/li[@title='All eTexts']/div[@class='contentstackimagediv']/a[@class='displaytab']/img[@title='All eTexts']"));
			return searchByeText_alleTextStack;
		}
		
		public static WebElement searchByNotes_alleTextNotesStack(WebDriver driver)
		{
			WebElement searchByNotes_alleTextNotesStack = driver.findElement(By.cssSelector("div#searchJournalBooks>ul.bookShelftable>li#allPagesID>div.contentstackimagediv >a.displaytab>img[alt='All eText Notes']"));
		//	WebElement searchByNotes_alleTextNotesStack = driver.findElement(By.xpath("//div[@id='searchJournalBooks']/ul[@class='bookShelftable']/li[@id='allPagesID']/div[@class='contentstackimagediv ']/a[@class='displaytab']/img[@alt='All eText Notes']"));
			return searchByNotes_alleTextNotesStack;
		}
		
		public static WebElement saveThisSearchButton(WebDriver driver)
		{
			WebElement saveThisSearchButton = driver.findElement(By.xpath("//span[@id='saveBtnDivID']/a[contains(.,'Save this Search')]"));
			return saveThisSearchButton;
		}
	}
	
	public static class SignOutPageElements
	{
		public static WebElement signOutLink(WebDriver driver)
		{
			WebElement signOutLink = driver.findElement(By.id("signout"));
			return signOutLink;
		}
	}
	
	public static class SearchAndBrowseLibraryPageElements
	{
		public static WebElement searchAndBrowseLibraryLink(WebDriver driver)
		{
			WebElement linkSearchAndBrowseLibrary = driver.findElement(By.cssSelector("div.searchBrowseLink>a[title='Search and browse Library']"));
		//	WebElement linkSearchAndBrowseLibrary = driver.findElement(By.xpath("//div[@class='searchBrowseLink']/a[@title='Search and browse Library']"));
			return linkSearchAndBrowseLibrary;
		}
		
		public static WebElement signInLink(WebDriver driver)
		{
			WebElement signInLink = driver.findElement(By.xpath("//span[@class='headerLinks']/a"));
			return signInLink;
		}
		
		public static WebElement arrowButton(WebDriver driver)
		{
			WebElement arrow = driver.findElement(By.xpath("//li[@class='dropdown']/a/img[@class='dropDownArrow']"));
			return arrow;
		}
		
		public static List<WebElement> dropdownOptions(WebDriver driver)
		{
			List<WebElement> libraryDropdownOptions = driver.findElements(By.className("iconText"));
			return libraryDropdownOptions;
		}
		
		public static WebElement AlleTextStack(WebDriver driver)
		{
			WebElement alleTextStack = driver.findElement(By.xpath("//a[@class='displaytab']/img[@alt='All eTexts']"));
			return alleTextStack;
		}
		
		public static List<WebElement> listOfBooksPresentInAlleTextStack(WebDriver driver)
		{
			List<WebElement> listOfBooksInAlleTextStack = driver.findElements(By.xpath("//div[@class='titleDiv']/label[1]"));
			return listOfBooksInAlleTextStack;
		}
		
		public static String moreInfoButtonOfBook(WebDriver driver)
		{
			String rawXPathOfMoreInfoButton = "//div[@class='titleDiv']/label[@title='BOOKNAME']/parent::div/following-sibling::div[@class='favoritePanePostion']/span/img";
			return rawXPathOfMoreInfoButton;
		}
		
		public static String xPathOfModelWindow(WebDriver driver)
		{
			String xPathOfmodelWindow = "//div[@class='modal-body']";
			return xPathOfmodelWindow;
		}
		
		public static WebElement closeButtonOfModelWindow(WebDriver driver)
		{
			WebElement closeButton = driver.findElement(By.xpath("//div[@class='book-right-panel']/i[@id='closebookmodal']"));
			return closeButton;
		}
		
		public static String bookNameOnMoreInfoPopUp(WebDriver driver)
		{
			WebElement bookNameOnMoreInfo = driver.findElement(By.xpath("//div[@class='bookModalBookInformation']/label[@class='bookModalBookTitle']"));
			String bookName = bookNameOnMoreInfo.getAttribute("title");
			return bookName;
		}
		
		public static String authorNameOnMoreInfoPopUp(WebDriver driver)
		{
			WebElement authorNameOnMoreInfo = driver.findElement(By.xpath("//div[@class='bookModalBookInformation']/label[@class='bookModalBookAuthor']"));
			String authorName = authorNameOnMoreInfo.getText();
			return authorName;
		}
		
		public static String copyrightYeareOnMoreInfoPopUp(WebDriver driver)
		{
			WebElement copyrightYearOnMoreInfo = driver.findElement(By.xpath("//div[@class='bookModalBookInformation']/label[@class='bookModalBookAdditionalInfo']"));
			String copyrightYear = copyrightYearOnMoreInfo.getText();
			return copyrightYear;
		}
		
		public static String bookDescriptionOnMoreInfoPopUp(WebDriver driver)
		{
			WebElement bookDescriptionOnMoreInfo = driver.findElement(By.xpath("//div[@class='book-info-lower-half']/label[@class='bookModalBookDecription']"));
			String bookDescription = bookDescriptionOnMoreInfo.getText();
			return bookDescription;
		}
		
		public static WebElement previewChaptersLabel(WebDriver driver)
		{
			WebElement previewChaptersLabel = driver.findElement(By.xpath("//div[@class='previewDiv']/div[@class='bookpreviewLabel']"));
			return previewChaptersLabel;
		}
		
		public static WebElement previewChaptersDropdown(WebDriver driver)
		{
			WebElement previewChaptersDropdown = driver.findElement(By.xpath("//div[@id='chapterPreviewOption']/div/div[@class='previewChapterOptionlabel']"));
			return previewChaptersDropdown;
		}
		
		public static List<WebElement> previewChaptersDropdownOptions(WebDriver driver)
		{
			List<WebElement> previewChaptersDropdown = driver.findElements(By.xpath("//div[@id='chapterPreviewOption']/div/ul/li"));
			return previewChaptersDropdown;
		}
		
		public static WebElement arrowPreviewChapterDropdown(WebDriver driver)
		{
			WebElement arrowPreviewChapter = driver.findElement(By.xpath("//div[@class='previewChapterOptionlabel']/img"));
			return arrowPreviewChapter;
		}
		
		public static WebElement goButton(WebDriver driver)
		{
			WebElement goButton = driver.findElement(By.xpath("//div[@id='chapterPreviewOption']/div/div[@id='gobutton']"));
			return goButton;
		}
		
		public static String chapterPreviewModel(WebDriver driver)
		{
			String xPathOfChapterPreviewModel = "//div[@id='ChapterPreviewModal']";
			return xPathOfChapterPreviewModel;
		}
		
		public static WebElement bookNameOnChapterPreview(WebDriver driver)
		{
			WebElement bookNameOnChapterPreview = driver.findElement(By.cssSelector("div.previewtitledetails>div#titledetails>label.previewtitle>span"));
		//	WebElement bookNameOnChapterPreview = driver.findElement(By.xpath("//div[@class='previewtitledetails']/div[@id='titledetails']/label[@class='previewtitle']/span"));
			return bookNameOnChapterPreview;
		}
		
		public static WebElement authorLastNameOnChapterPreview(WebDriver driver)
		{	
			WebElement authorLastNameOnChapterPreview = driver.findElement(By.xpath("//div[@class='previewtitledetails']/div[@id='titledetails']/label[@class='previewauthor authorname']/span"));
			return authorLastNameOnChapterPreview;
		}
		
		public static WebElement pageNumberOnChapterPreview(WebDriver driver)
		{
			WebElement pageNumberOnChapterPreview = driver.findElement(By.cssSelector("div.previewtitledetails>div#titledetails>span#pagenum"));
		//	WebElement pageNumberOnChapterPreview = driver.findElement(By.xpath("//div[@class='previewtitledetails']/div[@id='titledetails']/span[@id='pagenum']"));
			return pageNumberOnChapterPreview;
		}
		
		public static WebElement showTwoPageViewButton(WebDriver driver)
		{
			WebElement twoPageViewButton = driver.findElement(By.xpath("//div[@id='utilbuttons']/span[@id='twopage']/img"));
			return twoPageViewButton;
		}
		
		public static WebElement showOnePageViewButton(WebDriver driver)
		{
			WebElement onePageViewButton = driver.findElement(By.xpath("//div[@id='utilbuttons']/span[@id='onepage']/img"));
			return onePageViewButton;
		}
		
		public static WebElement zoomOutButton(WebDriver driver)
		{
			WebElement zoomOutButton = driver.findElement(By.xpath("//div[@id='utilbuttons']/span[@title='Zoom Out']/img"));
			return zoomOutButton;
		}
		
		public static WebElement zoomInButton(WebDriver driver)
		{
			WebElement zoomInButton = driver.findElement(By.xpath("//div[@id='utilbuttons']/span[@title='Zoom In']/img"));
			return zoomInButton;
		}
		
		public static WebElement nextPageButton(WebDriver driver)
		{
			WebElement nextPageButton = driver.findElement(By.xpath("//div[@class='modal-header']/a[@id='next']"));
			return nextPageButton;
		}
		
		public static WebElement previousPageButton(WebDriver driver)
		{
			WebElement previousPageButton = driver.findElement(By.xpath("//div[@class='previewtitledetails']/div[@id='titledetails']/a"));
			return previousPageButton;
		}
		
		public static String xPathOfChapterEntryToBeClickedInPreviewChapterDropdown(WebDriver driver)
		{
			String xPathOfChapterEntry = "//ul[@class='dropdown-menu previewChaptersOptionlabeldropdown']/li[2]/a[@title='CHAPTER_NAME']";
			return xPathOfChapterEntry;
		}

	}
}