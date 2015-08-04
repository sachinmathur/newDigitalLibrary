package sac.dl.utility;

import sac.dl.base.TestBaseSetUp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfAction;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class CustomReportGenerator implements ITestListener {

	private static WebDriver driver;
	
	List fileList;
	
	int numberOfPassTestCases= 0;
	int numberOfFailedTestCases= 0;
	int numberOfSkippedTestCases= 0;

	/**
	 * Document
	 */
	private Document document = null;

	/**
	 * PdfPTables
	 */
	PdfPTable successTable = null, failTable = null, executionMetaDataTable = null, testResultSummaryTable = null;

	/**
	 * throwableMap
	 */
	private HashMap<Integer, Throwable> throwableMap = null;

	private int nbExceptions = 0;

	public CustomReportGenerator() {

		this.document = new Document();
		this.throwableMap = new HashMap<Integer, Throwable>();
		fileList = new ArrayList();
	}

	public void onTestSuccess(ITestResult result) {
		log("onTestSuccess("+result+")");

		String testcaseStatus = "Pass";
		String testcaseName = result.getName();
		try {
			updateTestResultsInSuite(testcaseStatus, testcaseName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		numberOfPassTestCases = numberOfPassTestCases + 1;

		if (successTable == null) {
			this.successTable = new PdfPTable(new float[]{.3f, .3f, .1f, .3f});
			Paragraph p = new Paragraph("PASSED TESTS", new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(p);
			cell.setColspan(4);
			cell.setBackgroundColor(Color.GREEN);
			this.successTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Class"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Method"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Time (ms)"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.successTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Exception"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.successTable.addCell(cell);
		}

		PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.successTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + (result.getEndMillis()-result.getStartMillis())));
		this.successTable.addCell(cell);

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
			this.nbExceptions++;
			Paragraph excep = new Paragraph(
					new Chunk(throwable.toString(), 
							new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE)).
							setLocalGoto("" + throwable.hashCode()));
			cell = new PdfPCell(excep);
			this.successTable.addCell(cell);
		} else {
			this.successTable.addCell(new PdfPCell(new Paragraph("")));
		}
	}

	public void onTestFailure(ITestResult result) {
		log("onTestFailure("+result+")");

		numberOfFailedTestCases = numberOfFailedTestCases+ 1;
		String testcaseStatus = "Fail";

		String testcaseName = result.getName();
		try 
		{
			takeScreenshotOnFailure(testcaseName);
			updateTestResultsInSuite(testcaseStatus, testcaseName);
		} 

		catch (Exception e) 
		{
			e.printStackTrace();
		}


		if (this.failTable == null) {
			this.failTable = new PdfPTable(new float[]{.3f, .3f, .1f, .3f, .3f});
			this.failTable.setTotalWidth(25f);
			Paragraph p = new Paragraph("FAILED TESTS", new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			p.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(p);
			cell.setColspan(5);
			cell.setBackgroundColor(Color.RED);
			this.failTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Class"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Method"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Time (ms)"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Exception"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.failTable.addCell(cell);
			cell = new PdfPCell(new Paragraph("Screenshot"));
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.failTable.addCell(cell);
		}

		PdfPCell cell = new PdfPCell(new Paragraph(result.getTestClass().toString()));
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph(result.getMethod().toString()));
		this.failTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("" + (result.getEndMillis()-result.getStartMillis())));
		this.failTable.addCell(cell);

		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			this.throwableMap.put(new Integer(throwable.hashCode()), throwable);
			this.nbExceptions++;
			Paragraph excep = new Paragraph(throwable.toString());			
			cell = new PdfPCell(excep);
			this.failTable.addCell(cell);
		} else {
			this.failTable.addCell(new PdfPCell(new Paragraph("")));
		}

		String file = getScreenshotLocation(testcaseName);
		Chunk imdb = new Chunk("[Click to open screenshot]", new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.UNDERLINE));
		imdb.setAction(new PdfAction(file));
		cell = new PdfPCell(new Paragraph(imdb));
		this.failTable.addCell(cell);
	}

	public void onTestSkipped(ITestResult result) {
		log("onTestSkipped("+result+")");

		numberOfSkippedTestCases = numberOfSkippedTestCases + 1;
	}

	public void addExecutionDetailsIntoPDFReport(ITestContext context)
	{
		if(this.executionMetaDataTable ==null)
		{
			this.executionMetaDataTable = new PdfPTable(new float[]{.3f, .3f, .3f, .3f, .3f});
			this.executionMetaDataTable.setTotalWidth(25f);
			Paragraph headerOfSummaryTable = new Paragraph(context.getName(), new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			headerOfSummaryTable.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(headerOfSummaryTable);
			cell.setColspan(5);
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.executionMetaDataTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Browser"));
			cell.setBackgroundColor(Color.MAGENTA);
			this.executionMetaDataTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Release"));
			cell.setBackgroundColor(Color.MAGENTA);
			this.executionMetaDataTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Iteration"));
			cell.setBackgroundColor(Color.MAGENTA);
			this.executionMetaDataTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Build"));
			cell.setBackgroundColor(Color.MAGENTA);
			this.executionMetaDataTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Executed on"));
			cell.setBackgroundColor(Color.MAGENTA);
			this.executionMetaDataTable.addCell(cell);
		}

		String browserName = TestBaseSetUp.setBrowserName();
		String releaseName = TestBaseSetUp.setReleaseName();
		String iterationNumber = TestBaseSetUp.setIterationNumber();
		String buildVersion = TestBaseSetUp.setBuildVersion();

		PdfPCell cell = new PdfPCell(new Paragraph(browserName));
		this.executionMetaDataTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(releaseName));
		this.executionMetaDataTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(iterationNumber));
		this.executionMetaDataTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(buildVersion));
		this.executionMetaDataTable.addCell(cell);

		cell = new PdfPCell(new Paragraph(new Date().toString()));
		this.executionMetaDataTable.addCell(cell);
	}

	public void onStart(ITestContext context) {
		log("onStart("+context+")");
		try {

			FileOutputStream PDFReport = new FileOutputStream(CreateOutputFileDirectories.buildDir+"\\"+context.getName()+".pdf");
			PdfWriter.getInstance(this.document, PDFReport);

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.document.open();

		CustomReportGenerator.driver = TestBaseSetUp.setDriver();
	}

	public void onFinish(ITestContext context) {
		log("onFinish("+context+")");

		try {
			addExecutionDetailsIntoPDFReport(context);
			prepareSummaryReport();

			this.executionMetaDataTable.setSpacingBefore(15f);
			this.document.add(this.executionMetaDataTable);
			this.executionMetaDataTable.setSpacingAfter(15f);

			this.testResultSummaryTable.setSpacingBefore(15f);
			this.document.add(this.testResultSummaryTable);
			this.testResultSummaryTable.setSpacingAfter(15f);

			if (this.failTable != null) {
				log("Added fail table");
				this.failTable.setSpacingBefore(15f);
				this.document.add(this.failTable);
				this.failTable.setSpacingAfter(15f);
			}

			if (this.successTable != null) {
				log("Added success table");
				this.successTable.setSpacingBefore(15f);
				this.document.add(this.successTable);
				this.successTable.setSpacingBefore(15f);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Paragraph p = new Paragraph("EXCEPTIONS SUMMARY",
				FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new Color(255, 0, 0)));
		try {
			this.document.add(p);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}

		Set<Integer> keys = this.throwableMap.keySet();

		assert keys.size() == this.nbExceptions;

		for(Integer key : keys) {
			Throwable throwable = this.throwableMap.get(key);

			Chunk chunk = new Chunk(throwable.toString(),
					FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 0, 0)));
			chunk.setLocalDestination("" + key);
			Paragraph throwTitlePara = new Paragraph(chunk);
			try {
				this.document.add(throwTitlePara);
			} catch (DocumentException e3) {
				e3.printStackTrace();
			}

			StackTraceElement[] elems = throwable.getStackTrace();
			for(StackTraceElement ste : elems) {
				Paragraph throwParagraph = new Paragraph(ste.toString());
				try {
					this.document.add(throwParagraph);
				} catch (DocumentException e2) {
					e2.printStackTrace();
				}
			}
		}

		this.document.close();
	}
	
	public void prepareSummaryReport()
	{
		log("Preparing Test Summary Report.");

		if(this.testResultSummaryTable ==null)
		{
			this.testResultSummaryTable = new PdfPTable(new float[]{.3f, .3f, .3f, .3f});
			this.testResultSummaryTable.setTotalWidth(25f);
			Paragraph headerOfSummaryTable = new Paragraph("Summary of Test Execution:", new Font(Font.TIMES_ROMAN, Font.DEFAULTSIZE, Font.BOLD));
			headerOfSummaryTable.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cell = new PdfPCell(headerOfSummaryTable);
			cell.setColspan(4);
			cell.setBackgroundColor(Color.LIGHT_GRAY);
			this.testResultSummaryTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("Total # of Testcases"));
			cell.setBackgroundColor(Color.CYAN);
			this.testResultSummaryTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("# of Testcases Passed"));
			cell.setBackgroundColor(Color.CYAN);
			this.testResultSummaryTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("# of Testcases Failed"));
			cell.setBackgroundColor(Color.CYAN);
			this.testResultSummaryTable.addCell(cell);

			cell = new PdfPCell(new Paragraph("# of Testcases Skipped"));
			cell.setBackgroundColor(Color.CYAN);
			this.testResultSummaryTable.addCell(cell);
		}

		int totalTestcases = numberOfPassTestCases + numberOfFailedTestCases + numberOfSkippedTestCases;
		PdfPCell cell = new PdfPCell(new Paragraph("#"+totalTestcases));
		this.testResultSummaryTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("#"+numberOfPassTestCases));
		this.testResultSummaryTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("#"+numberOfFailedTestCases));
		this.testResultSummaryTable.addCell(cell);

		cell = new PdfPCell(new Paragraph("#"+numberOfSkippedTestCases));
		this.testResultSummaryTable.addCell(cell);
	}

	public void updateTestResultsInSuite(String testcaseStatus, String testcaseName) throws Exception
	{
		Workbook runningTestsuite = Workbook.getWorkbook(new File(CreateOutputFileDirectories.TestResultsPath));
		WritableWorkbook writableTestsuite = Workbook.createWorkbook(new File(CreateOutputFileDirectories.TestResultsPath), runningTestsuite);
		WritableSheet testCaseSheet = writableTestsuite.getSheet(ReadFromPropertiesFile.prop.getProperty("TestCaseSheet"));
		
		for(int row=1;row<CreateOutputFileDirectories.resultSheet.getRows();row++)
		{	
			String automationMethodInSheet = CreateOutputFileDirectories.resultSheet.getCell(3,row).getContents();

			if(testcaseName.equals(automationMethodInSheet))
			{
				Label markStatusInResultColumn =new Label(4,row,testcaseStatus);
				testCaseSheet.addCell(markStatusInResultColumn);
			}
		}

		writableTestsuite.write();
		writableTestsuite.close();
		runningTestsuite.close();
	}

	public static String setScreenshotLocation(String testcaseName)
	{
		String file = CreateOutputFileDirectories.buildDir +ReadFromPropertiesFile.prop.getProperty("FailureScreenshotDir")+testcaseName+".jpg";
		return file;
	}

	public static String getScreenshotLocation(String testcaseName)
	{
		String file = ReadFromPropertiesFile.prop.getProperty("FailureScreenshotDir")+testcaseName+".jpg";
		return file;
	}

	public static void takeScreenshotOnFailure(String testName) throws Exception
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String file = CustomReportGenerator.setScreenshotLocation(testName);
		FileUtils.copyFile(scrFile, new File(file));
	}

	public static void log(Object o){
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
