package sac.dl.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class CreateOutputFileDirectories {

	protected static String TestResultsPath = "";
	protected static String resultSuiteName = "";
	protected static String MasterTestSuitePath= "";
	protected static String TestSuiteSheetName = "";
	protected static String applicationDirectory;
	protected static String browserDirectory = "";
	protected static String releaseDirectory = "";
	protected static String iterationDirectory = "";
	protected static String buildDirectory = "";

	protected static Workbook masterTestSuite;
	protected static Sheet masterTestCases;
	protected static WritableWorkbook resultTestSuite;
	protected static WritableSheet resultSheet;
	protected static File buildDir;

	public static void createResultDir(String browserToBeUsed, String release, String iteration, String build)
	{
		applicationDirectory = ReadFromPropertiesFile.prop.getProperty("applicationDirectory");
		File appDir = new File(applicationDirectory);

		if(!appDir.exists())
		{
			appDir.mkdir();
		}

		browserDirectory = applicationDirectory + browserToBeUsed + "\\";
		File browserDir = new File(browserDirectory);

		if(!browserDir.exists())
		{
			browserDir.mkdir();
		}

		releaseDirectory = browserDirectory + "R" + release + "\\";
		File releaseDir = new File(releaseDirectory);

		if(!releaseDir.exists())
		{
			releaseDir.mkdir();
		}

		iterationDirectory = releaseDirectory + "Itr" + iteration + "\\";
		File iterationDir = new File(iterationDirectory);

		if(!iterationDir.exists())
		{
			iterationDir.mkdir();
		}

		buildDirectory = iterationDirectory + "build" + build + "\\";
		buildDir = new File(buildDirectory);

		if(!buildDir.exists())
		{
			buildDir.mkdir();
		}

		resultSuiteName = "Testsuite_"+"Rel_"+release+"_Itr_"+iteration+"_Build_"+build+".xls";
		TestResultsPath = buildDir + "\\"+ resultSuiteName;
	}

	public static void copyResultSuiteFromMasterTestSuite() throws Exception
	{	
		TestSuiteSheetName = ReadFromPropertiesFile.prop.getProperty("TestSuiteSheetName");
		MasterTestSuitePath = ReadFromPropertiesFile.prop.getProperty("MasterTestSuitePath");

		FileInputStream testSuite = new FileInputStream(MasterTestSuitePath);
		masterTestSuite = Workbook.getWorkbook(testSuite);
		masterTestCases = masterTestSuite.getSheet(TestSuiteSheetName);
		String a[][] = new String[masterTestCases.getRows()][masterTestCases.getColumns()];

		FileOutputStream result = new FileOutputStream(TestResultsPath);
		resultTestSuite = Workbook.createWorkbook(result);
		resultTestSuite.createSheet(TestSuiteSheetName,0);
		resultSheet = resultTestSuite.getSheet("Testsuite");

		Map<CellFormat, WritableCellFormat> definedFormats = new HashMap<CellFormat, WritableCellFormat>();

		for(int rows=0;rows<masterTestCases.getRows();rows++)
		{
			for(int columns=0;columns<masterTestCases.getColumns();columns++)
			{	
					a[rows][columns] = masterTestCases.getCell(columns, rows).getContents();
					Label testcases = new Label(columns, rows,a[rows][columns]);
					resultSheet.addCell(testcases);
			}
		}

		resultTestSuite.write();
		resultTestSuite.close();
	}
}