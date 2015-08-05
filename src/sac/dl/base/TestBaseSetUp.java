package sac.dl.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import sac.dl.utility.CreateOutputFileDirectories;
import sac.dl.utility.ReadFromPropertiesFile;
import sac.dl.utility.SendEmail;
import sac.dl.utility.ZipOutputFiles;

public class TestBaseSetUp {

	protected static WebDriver driver;
	protected Logger log;
	
	public static String release = ""; 
	public static String iteration = "";
	public static String build = "";
	public static String browserToLaunch="";

	@BeforeSuite
	@Parameters({ "AppURL", "Browser", "Release", "Iteration", "Build" })
	public void setUp(String appURL, String browsers, String release, String iteration, String build, final ITestContext context) throws Exception
	{
		TestBaseSetUp.release = release;
		TestBaseSetUp.iteration = iteration;
		TestBaseSetUp.build = build;
		
		ReadFromPropertiesFile readProp = new ReadFromPropertiesFile();
		readProp.loadConfigFile();

		String[] browsersToBeTestedOn = browsers.split("\\s*,\\s*");
		
		int numberOfBrowsers = browsersToBeTestedOn.length;
		
		for(int index = 0; index<numberOfBrowsers;index++)
		{
			TestBaseSetUp.browserToLaunch = browsersToBeTestedOn[index];
			
			CreateOutputFileDirectories.createResultDir(browserToLaunch,release,iteration,build);
			CreateOutputFileDirectories.copyResultSuiteFromMasterTestSuite();
			
			if(browserToLaunch.equals("firefox"))
			{
				getFireFoxDriver();
			}
			
			else if(browserToLaunch.equals("chrome"))
			{
				getChromeDriver();
			}
			
			else if(browserToLaunch.equals("ie"))
			{
				getInternetExplorerDriver();
			}
			
			else if(browserToLaunch.equals("All"))
			{
				getFireFoxDriver();
				getChromeDriver();
				getInternetExplorerDriver();
			}
		}
		
		driver.manage().window().maximize();
		driver.get(appURL);
		TestBaseSetUp.setDriver();
		this.setLogger();
	}

	public static WebDriver setDriver()
	{
		return driver;
	}

	public Logger setLogger()
	{
		this.log = Logger.getLogger("applicationLogger");
		return log;
	}
	
	public static String setBrowserName()
	{
		return browserToLaunch;
	}
	
	public static String setReleaseName()
	{
		return release;
	}
	
	public static String setIterationNumber()
	{
		return iteration;
	}
	
	public static String setBuildVersion()
	{
		return build;
	}

	public static void getFireFoxDriver()
	{
		ProfilesIni profile = new ProfilesIni();
		FirefoxProfile firefoxProfile = profile.getProfile("FirefoxAutomationProfile");
		driver=new FirefoxDriver(firefoxProfile);
	}
	
	public static void getChromeDriver()
	{
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		String chromeDriverLocation = ReadFromPropertiesFile.prop.getProperty("ChromeDriverLocation");
		System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
		driver = new ChromeDriver(chromeCapabilities);
	}
	
	public static void getInternetExplorerDriver()
	{
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability("ignoreZoomSetting", true); // This will make script work even if zoom level of browser is other than 100%.
		
		String ie64BitDriverLocation = ReadFromPropertiesFile.prop.getProperty("IE64BitDriverLocation");
		
		System.setProperty("webdriver.ie.driver", ie64BitDriverLocation);
		driver = new InternetExplorerDriver(ieCapabilities);
	}
	
	@AfterSuite
	public void tearDown() throws Exception
	{
		if(driver!= null)
		{
			log.info("Testsuite completed..closing the driver");

			try{
				driver.close();
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");	
				driver.quit();
			}
			catch(Exception e){}
		}

		ZipOutputFiles.createZip();		
	//	SendEmail.sendPDFReportByEmail("", "", "sachin.mathur22@gmail.com", "PDF Report", "");

	}
}