package sac.dl.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import sac.dl.utility.CreateOutputFileDirectories;
import sac.dl.utility.ReadFromPropertiesFile;

public class TestBaseSetUp {

	protected static WebDriver driver;
	protected Logger log;
	
	public static String browserToBeUsed = "";
	public static String release = ""; 
	public static String iteration = "";
	public static String build = "";

	@BeforeSuite
	@Parameters({ "AppURL", "Browser", "Release", "Iteration", "Build" })
	public void setUp(String appURL, String browserToBeUsed, String release, String iteration, String build, final ITestContext context) throws Exception
	{
		System.out.println("Test Jenkins.");
		TestBaseSetUp.browserToBeUsed = browserToBeUsed;
		TestBaseSetUp.release = release;
		TestBaseSetUp.iteration = iteration;
		TestBaseSetUp.build = build;
		
		ReadFromPropertiesFile readProp = new ReadFromPropertiesFile();
		readProp.loadConfigFile();
		CreateOutputFileDirectories.createResultDir(browserToBeUsed,release,iteration,build);
		CreateOutputFileDirectories.copyResultSuiteFromMasterTestSuite();

		driver=new FirefoxDriver();
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
		return browserToBeUsed;
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

	@AfterSuite
	public void tearDown() throws Exception
	{	
		//	sendPDFReportByEmail("", "", "sachin.mathur22@gmail.com", "PDF Report", "");

		if(driver!= null)
		{
			log.info("Testsuite completed..closing the driver");
			driver.close();
		
			try{
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Thread.sleep(5000);
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
				Runtime.getRuntime().exec("taskkill /F /IM WerFault.exe");
				
				Thread.sleep(5000);
				driver.quit();
			}
			catch(Exception e){}
			driver=null;
		}

	}
}