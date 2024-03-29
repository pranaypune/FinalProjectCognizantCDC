package testCases;


import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookShelvesPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_01_NavigateToBookshelvesPage extends BaseClass {
	
	HomePage homePage = new HomePage(driver);
	BookShelvesPage bookShelves = new BookShelvesPage(driver);
	
	@Test(priority = 0,groups= {"Smoke Test","Master"})
	public void tc_001_isBookShelvesButtonPresent() {
		logger = LogManager.getLogger(this.getClass());
		logger.info("*** Starting of TS_01_NavigateToBookshelvesPage ***");
		try{
			homePage = new HomePage(driver);
			boolean status = homePage.isBookshelvesVisible();
			Assert.assertEquals(status, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 1, dependsOnMethods = {"tc_001_isBookShelvesButtonPresent"},groups= {"Smoke Test","Master"})
	public void tc_002_verifyBookshelvesPage() {
		try{
			homePage = new HomePage(driver);
			homePage.clickBookshelvesButton();
			
			bookShelves = new BookShelvesPage(driver);
			String actualHeading = bookShelves.getHeading();
			String expectedHeading = "Bookshelves";
			Assert.assertEquals(actualHeading, expectedHeading);
			logger.info("Bookshelve page loaded successfully");
		}
		catch(Exception e) {
			logger.info("Bookshelve page loading failed");
			Assert.fail(e.getMessage());
		}
		logger.info("*** Ending of TS_01_NavigateToBookshelvesPage ***");
	}
}
