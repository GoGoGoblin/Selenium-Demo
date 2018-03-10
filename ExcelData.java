package SeleniumTest1;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelData {
	
	/* class attributes */
	private String inputData;
	private String outputData;
	
	/* methods to access class attributes */
    public String getInputData()
    {
    	return this.inputData;
    }
    
    public String getOutputData()
    {
    	return this.outputData;
    }
	
	public ExcelData()
	{
		this.inputData = null;
		this.outputData = null;
	}
		
		/* This method takes a test type (positive/negative) and reads input data stored in an excel file */
	  	public void readInputData(String filePath, String sheetName, String testType)
	  	{   	
	  		try{
	    		/* instantiate File object */
			    File file =  new File(filePath);
		
			    /* instantiate FileInputStream object to read data from a file */
			    FileInputStream inputStream = new FileInputStream(file);
		
			    /* instantiate XSSFWorkbook object to read data from an excel file */
			    XSSFWorkbook book = new XSSFWorkbook(inputStream);
		
		
			    /* instantiate XSSFSheet object to access to the specified sheet */
			    XSSFSheet sheet = book.getSheet(sheetName);
			    
			    String inData = null; //initialize return value
			    
			    int rowNum = sheet.getLastRowNum();   // get number of rows in the sheet
			    
			    
			    /* check value of each cell until it finds type of test to be executed */
		        for (int i = 0; i < rowNum + 1; i++) 
		        {
		        	XSSFRow row = sheet.getRow(i);
		        	
		        	if(row != null)
		        	{
		        		int colNum = row.getLastCellNum();   // get number of columns in the row
		            
		        		for (int j = 0; j < colNum + 1; j++)
		        		{
		        			XSSFCell cell = row.getCell(j);
		        				
		        			if(cell != null)
		        			{     	
		        				/* type of test found */
			        			if (cell.getStringCellValue().equalsIgnoreCase(testType))  
			        			{
			        				XSSFCell targetCell = row.getCell(++j);  // goto the next cell
			        				inData = targetCell.getStringCellValue();  // get the value of the cell
			        				break;
			        			}
		        			}
		        		}
		        	}
		        }
		        
		        this.inputData = inData;  //set obtained data to class attribute   	      
	  		}
	  		
			catch (Exception e) 
			{
				System.out.println(e);
			}
	    }
	  	
	  	/* This method takes a test type (positive/negative) and reads expected data stored in an excel file */
	  	public void readOutputData(String filePath, String sheetName, String testType)
	  	{
	  		try{
	    		/* instantiate File object */
			    File file =  new File(filePath);
		
			    /* instantiate FileInputStream object to read data from a file */
			    FileInputStream inputStream = new FileInputStream(file);
		
			    /* instantiate XSSFWorkbook object to read data from an excel file */
			    XSSFWorkbook book = new XSSFWorkbook(inputStream);
		
		
			    /* instantiate XSSFSheet object to access to the specified sheet */
			    XSSFSheet sheet = book.getSheet(sheetName);
			    
			    String outData = null;  //initialize return value
			    
			    int rowNum = sheet.getLastRowNum();  // get number of rows in the sheet
			    
			    /* check value of each cell until it finds type of test to be executed */
		        for (int i = 0; i < rowNum + 1; i++) 
		        {
		        	XSSFRow row = sheet.getRow(i);   
		        	
		        	if(row != null)
		        	{
		        		int colNum = row.getLastCellNum();  // get number of columns in the row
		            
		        		for (int j = 0; j < colNum + 1; j++)
		        		{
		        			XSSFCell cell = row.getCell(j);
		        				
		        			if(cell != null)
			            	
		        				/* type of test found */
			        			if (cell.getStringCellValue().equalsIgnoreCase(testType))
			        			{
			        				j = j + 2; // target value is stored in the cell after next 
			        				XSSFCell targetCell = row.getCell(j);
			        				outData = targetCell.getStringCellValue();
			        				break;
			        			}
		        			}
		        		}
		        	}
		       
		        this.outputData = outData;  //set obtained data to class attribute
	  		}
	
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}
			    

    
