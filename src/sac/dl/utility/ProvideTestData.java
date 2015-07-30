package sac.dl.utility;

import java.io.File;
import java.lang.reflect.Method;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.DataProvider;

public class ProvideTestData {

	@DataProvider(name = "ExcelDataProvider")
	public static Object[][] createData(Method method) throws Exception
	{	
		String tableName =method.getName();
		Object[][] retUserCredentials=getTableArray(ReadFromPropertiesFile.prop.getProperty("TestDataFile"),ReadFromPropertiesFile.prop.getProperty("TestDataSheet"),tableName);
		return (retUserCredentials);
	}
	
	public static String[][] getTableArray(String excelFilePath, String excelSheetName, String tableName) throws Exception
	{
		String[][] tabArray=null;
		Workbook workbook = Workbook.getWorkbook(new File(excelFilePath));
		Sheet sheet = workbook.getSheet(excelSheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow=tableStart.getRow();
		startCol=tableStart.getColumn();
		
		Cell tableEnd=sheet.findCell(tableName, startCol+1, startRow+1, 100,64000,false);
		
		endRow=tableEnd.getRow();
		endCol=tableEnd.getColumn();

		tabArray=new String[endRow-startRow-1][endCol-startCol-1];
		ci=0;
		
		for (int i=startRow+1;i<endRow;i++,ci++){
            cj=0;
            for (int j=startCol+1;j<endCol;j++,cj++){
                tabArray[ci][cj]=sheet.getCell(j,i).getContents();
            }
        }
		
		return (tabArray);
	}
}