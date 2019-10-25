package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	//Read Excel Path
	public ExcelFileUtil() throws Throwable
	{
	 FileInputStream fis=new FileInputStream("D:\\Sudhakar\\ERP_Stock\\TestInput\\Supplier.xlsx");
	 wb=WorkbookFactory.create(fis);
	}
	//count no of rows from sheet
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	//count no of columns in row
	public int colCount(String sheetname)
	{ return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	//Get cell data from sheet
	public String getdata (String sheetname,int row,int column)	{
		String data="";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
{
	int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	
	//convert celldata numeric column into string
data =String.valueOf(celldata);
}else{
	data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
}
 return data;
 }
	// To write into Workbook
public void setCellData(String sheetname,int row,int column,String status) throws Throwable
{
	//get sheet from wb
	Sheet ws=wb.getSheet(sheetname);
	//get row from sheet
	Row rownum=ws.getRow(row);
	// create cell in row
	Cell cell=rownum.createCell(column);
	//write status into cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		//create a cell style
		CellStyle style=wb.createCellStyle();
		//create a font
		Font font=wb.createFont();
		//apply colour to the text
		font.setColor(IndexedColors.GREEN.getIndex());
	//apply bold to the text
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);
	}else if(status.equalsIgnoreCase("Fail"))
			{
		//create a cell style
		CellStyle style=wb.createCellStyle();
		//create a font
		Font font=wb.createFont();
		//apply colour to the text
		font.setColor(IndexedColors.RED.getIndex());
	//apply bold to the text
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);
	}else if(status.equalsIgnoreCase("NotExecuted"))
{
		//create a cell style
		CellStyle style=wb.createCellStyle();
		//create a font
		Font font=wb.createFont();
		//apply colour to the text
		font.setColor(IndexedColors.BLUE.getIndex());
	//apply bold to the text
		font.setBold(true);
		//set font
		style.setFont(font);
		//set cell style
		rownum.getCell(column).setCellStyle(style);

}
FileOutputStream fos=new FileOutputStream("D:\\Sudhakar\\ERP_Stock\\TestOutput\\Datadriven.xlsx");
wb.write(fos);
fos.close();
}
}










