package testCases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookShelvesPage;
import testBase.BaseClass;
import utilities.AddFluentWait;

public class TS_02_VisibilityOfBookshelvesFilters extends BaseClass {
	
	AddFluentWait wait = new AddFluentWait();
	BookShelvesPage bookShelves = new BookShelvesPage(driver);
	
	@Test(priority = 0,groups= {"Sanity Test","Master"})
	public  void tc_003_handlePopup() {
		logger = LogManager.getLogger(this.getClass());
		logger.info("*** Starting of TS_02_VisibilityOfBookshelvesFilters ***");
		try {
			bookShelves = new BookShelvesPage(driver);
			wait.waitForPopupToOpen(driver);
//			Thread.sleep(5000);
			bookShelves.handlePopup();
			logger.info("Pop-up handled successfully");
		}
		catch(Exception e) {
			System.out.println("No Pop-up appeared");
		}
	}
	
	@Test(priority = 1, dependsOnMethods = {"tc_003_handlePopup"},groups= {"Sanity Test","Master"})
	public void tc_004_isPriceFilterPresent() {
		try {
			boolean isPriceFilterVisible = bookShelves.isPriceFilterVisible();
			Assert.assertEquals(isPriceFilterVisible, true);	
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 2,groups= {"Sanity Test","Master"})
	public void tc_005_isStorageTypeFilterPresent() {
		try {
			boolean isStorageTypeFilterVisible = bookShelves.isStorageTypeFilterVisible();
			Assert.assertEquals(isStorageTypeFilterVisible, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 3,groups= {"Smoke Test","Master"})
	public void tc_006_isBrandFilterPresent() {
		try {
			boolean isBrandFilterVisible = bookShelves.isBrandFilterVisible();
			Assert.assertEquals(isBrandFilterVisible, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 4,groups= {"Regression Test","Master"})
	public void tc_007_isOutOfStockCheckboxPresent() {
		try {
			boolean isOutOfStockCheckboxVisible = bookShelves.isOutOfStockCheckboxVisible();
			Assert.assertEquals(isOutOfStockCheckboxVisible, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 5,groups= {"Regression Test","Master"})
	public void tc_008_isPriceFilterHoverable() {
		try {
			bookShelves.hoverOverPriceFilter();	
			wait.waitForPriceFilterToOpen(driver);
			boolean isPriceFilterHoverable = bookShelves.isPriceFilterOptionVisible();
			Assert.assertEquals(isPriceFilterHoverable, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
		
	@Test(priority = 6,groups= {"Regression Test","Master"})
	public void tc_009_isStorageTypeFilterHoverable() {
		try {
			bookShelves.hoverOverStorageTypeFilter();
			wait.waitForStorageFilterToOpen(driver);
			boolean isStorageTypeFilterOptionVisible = bookShelves.isStorageTypeFilterOptionVisible();
			Assert.assertEquals(isStorageTypeFilterOptionVisible, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
		logger.info("*** All filtes in Bookshelve page is iteractable ***");
		logger.info("*** Ending of TS_02_VisibilityOfBookshelvesFilters ***");
	}
}
