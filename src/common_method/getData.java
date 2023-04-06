package common_method;

import java.io.FileInputStream;
import java.lang.Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getData {

	public static ArrayList<String> getDataExel(String testSheetName, String testCaseName) throws IOException
	{
		ArrayList<String> arrayData = new ArrayList<String>();
		//step 1 open exel data file, by creating the object of fileinputstring
		FileInputStream fis = new FileInputStream("C:\\Users\\91914\\OneDrive\\Desktop\\SUA\\REST ASSURED\\testdata.xlsx");
	   
		//step 2 create the object of XSSWorkbook to open the exel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
	
		//step3 access the desired sheet
		//step3.1 fetch the count of sheets available in the exel file
		int countOfSheet = workbook.getNumberOfSheets();
		
		//step3.2 fetch the name of sheet and compare against desired sheetname
		for(int i=0; i<countOfSheet; i++) 
		{
		String sheetname =	workbook.getSheetName(i);
		if (sheetname.equalsIgnoreCase(testSheetName))
		{
			//step 4 access the sheet and iterate through rows to fetch the column in which testcasename is found
			XSSFSheet Sheet = workbook.getSheetAt(i);
			//step 4.1 create iterator for rows
			Iterator<Row> Rows = Sheet.iterator();
			Row firstRow= Rows.next();
			//step 4.2 create iterator for cells
			Iterator<Cell> Cells = firstRow.cellIterator();
			int j=0;
			int tc_column=0;
			//step 4.3 read the cell values of row no.1 to compare against the testcasename
			while(Cells.hasNext())
			{
				Cell cellvalue = Cells.next();
				if (cellvalue.getStringCellValue().equalsIgnoreCase("test_tc"))
				{
					tc_column= j;
				
				}
				j++;
			}
			//step 5 fetch the data for designeted testcase
			while(Rows.hasNext())
			{
				Row dataRow = Rows.next();
				if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testCaseName))
				{
					Iterator<Cell>dataCellvalue = dataRow.cellIterator();
					
					while(dataCellvalue.hasNext())
					{
						Cell dataOfcell = dataCellvalue.next();
						//method -1 to extract cell value by using try and catch
						/*try
						{
							 String testdata = dataOfcell.getStringCellValue();
							arrayData.add(testdata);
						}
						catch (IllegalStateException e)
						{
							//System.out.println(e);
							int inttestData = (int) dataOfcell.getNumericCellValue();
							String stringtestData = Integer.toString(inttestData);
							arrayData.add(stringtestData);
						}*/
						/*Method2:to extract cell value by if and else
						CellType dataType =dataOfcell.getCellType();//getCellType tell you datatype of values of cell
						
						if (dataType.toString() == "NUMERIC")
						{
							int inttestData=(int) dataOfcell.getNumericCellValue();
							String stringtestData= Integer.toString(inttestData);//convert to string with (toString)
							arrayData.add(stringtestData);
						}
						else if (dataType.toString()=="STRING")
						{
							String testData = dataOfcell.getStringCellValue();
							arrayData.add(testData);
						}*/
						/*Method3:extract the data by converting into string first
						String testData= dataOfcell.toString().replaceAll("\\.\\d+$","");
						arrayData.add(testData);
						System.out.println(testData);*/
						
						//Method4:Extract the data by using DataFormatter
						DataFormatter format = new DataFormatter();
						String testData=format.formatCellValue(dataOfcell);
						arrayData.add(testData);
						System.out.println(testData);

						 


					}
				}
			}
			
		}
		}
			
		return arrayData;
	}
	
}
