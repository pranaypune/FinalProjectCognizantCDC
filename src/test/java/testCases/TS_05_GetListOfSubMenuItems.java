package testCases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookShelvesPage;
import testBase.BaseClass;
import utilities.AddFluentWait;
import utilities.ExcelUtility;

public class TS_05_GetListOfSubMenuItems extends BaseClass {
	
	AddFluentWait wait = new AddFluentWait();
	BookShelvesPage bookShelves;
	String path = ".//dataTables/testResult.xlsx";
	ExcelUtility excelUtility ;
	
	@Test(priority = 0,groups= {"Smoke Test","Master"})
	public void tc_014_isHomeDecorMenuPresent() {
		logger = LogManager.getLogger(this.getClass());
		logger.info("*** Starting of TS_05_GetListOfSubMenuItems ***");
		try{
			bookShelves = new BookShelvesPage(driver);
			bookShelves.scrollToUlService();
			bookShelves.hoverOverLightingAndDecor();
			wait.waitForLightingAndDecorMenuToOpen(driver);
			boolean isHomeDecorMenuPresent = bookShelves.isHomeDecorMenuPresent();
			Assert.assertEquals(isHomeDecorMenuPresent, true);
			logger.info(" Home Decore menu and sub-menu loaded successfully ");
		}
		catch(Exception e) {
			logger.info(" Home Decore menu and sub-menu loading failed ");
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 1,groups= {"Sanity Test","Master"})
	public void tc_015_getHomeDecorSubMenuItems() {
		try {
			excelUtility = new ExcelUtility(path);
					
			bookShelves = new BookShelvesPage(driver);
			List<String> homeDecorSubMenuItems = bookShelves.homeDecorSubMenuItems();
			System.out.println("\nList of Sub-Menu items under the Home Decor Menu:");
			excelUtility.setCellData("SubMenuItems", 0, 0, "Sub-Menu items");
			logger.info(" Printing Home decore items on console ");
			logger.info(" Printing Home decore items on excel sheets ");
			for(int i = 0; i < homeDecorSubMenuItems.size(); i++) {
				System.out.println(homeDecorSubMenuItems.get(i));
				excelUtility.setCellData("SubMenuItems", i+1, 0, homeDecorSubMenuItems.get(i));
			}
			System.out.println("\n");
			driver.navigate().refresh();
		}
		catch(Exception e) {
			logger.info(" Printing Home decore items loading failed ");
			Assert.fail(e.getMessage());
		}
		logger.info("*** Ending of TS_05_GetListOfSubMenuItems ***");
	}

}
