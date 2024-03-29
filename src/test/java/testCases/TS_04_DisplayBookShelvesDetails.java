package testCases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookShelvesPage;
import testBase.BaseClass;
import utilities.AddFluentWait;
import utilities.ExcelUtility;

public class TS_04_DisplayBookShelvesDetails extends BaseClass {
	
	AddFluentWait wait = new AddFluentWait();
	BookShelvesPage bookShelves = new BookShelvesPage(driver);
	String path = ".//dataTables/testResult.xlsx";
	ExcelUtility excelUtility ;
	
	@Test(priority = 0,groups= {"Regression Test","Master"})
	public void tc_013_displayBookshelvesDetails() {
		logger = LogManager.getLogger(this.getClass());
		logger.info("*** Starting of TS_04_DisplayBookShelvesDetails ***");
		try {
			excelUtility = new ExcelUtility(path);
			List<String> productName = bookShelves.getProductName();
			List<String> productPrice = bookShelves.getProductPrice();
			
			excelUtility.setCellData("BookshelveDetails", 0, 0, "Bookshelve Name");
			excelUtility.setCellData("BookshelveDetails", 0, 1, "Bookshelve Price");
			logger.info(" Printing bookshelves on console ");
			logger.info(" Printing bookshelves on excel sheet ");
			for(int i = 0; i <=2; i++) {
				System.out.println(productName.get(i) + "\t\t" + productPrice.get(i));
				excelUtility.setCellData("BookshelveDetails", i+1, 0, productName.get(i));
				excelUtility.setCellData("BookshelveDetails", i+1, 1, productPrice.get(i));
			}
		
		}catch(Exception e) {
			logger.info(" Printing bookshelves on console got failed ");
			logger.info(" Printing bookshelves on excell sheet got failed ");
			Assert.fail(e.getMessage());
		}
		logger.info("*** Ending of TS_04_DisplayBookShelvesDetails ***");
	}
}
