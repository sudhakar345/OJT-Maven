package DriverPackage;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DatadrivenFramework {
WebDriver driver;
//Creating Reference object for function library
ERP_Functions erp=new ERP_Functions();

@BeforeTest
public void launchapp() throws Throwable
{
	String app=erp.launchUrl("http://webapp.qedge.com");
	System.out.println(app);
	Thread.sleep(2000);
	//calling login
	String login=erp.verifylogin("admin","master");
	System.out.println(login);
}
@Test
public void supplierscreation() throws Throwable
{
	//To all excel utill methods 
	ExcelFileUtil xl=new ExcelFileUtil();
	//count no.of rows in sheet
	int rc=xl.rowCount("Supplier");
	// count no.of columns
	int cc=xl.rowCount("Supplier");
	Reporter.log("no of rows are::"+rc+"  "+"no of column are::"+cc,true);
	for (int i = 1; i <=rc; i++)
	{
		        String sname=xl.getdata("Supplier", i, 0);
				String address=xl.getdata("Supplier", i, 1);
				String city=xl.getdata("Supplier", i, 2);
				String country=xl.getdata("Supplier", i, 3);
				String cperson=xl.getdata("Supplier", i, 4);
				String pnumber=xl.getdata("Supplier", i, 5);
				String mail=xl.getdata("Supplier", i, 6);
				String mnumber=xl.getdata("Supplier", i, 7);
				String note=xl.getdata("Supplier", i, 8);
				String results=erp.verifysupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, note);
	xl.setCellData("Supplier", i, 9, results);
	}
}
@AfterTest
public void logout()throws Throwable{
	String logoutapp=erp.verifyLogout();
	System.out.println(logoutapp);
	//driver.close();
	

	
	
}
}
