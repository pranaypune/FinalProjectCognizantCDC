package testCases;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BookShelvesPage;
import pageObjects.GiftCardsPage;
import testBase.BaseClass;
import utilities.AddFluentWait;
import utilities.PropertyReader;
import utilities.RandomInputGenerator;

public class TS_06_GiftCardCustomization extends BaseClass {

	AddFluentWait wait = new AddFluentWait();
	GiftCardsPage giftCards;
	BookShelvesPage bookShelves;
	RandomInputGenerator random = new RandomInputGenerator();
	PropertyReader propertyReader;
	
	@Test(priority = 0,groups= {"Smoke Test","Master"})
	public void tc_016_validateGiftCardButton() {
		logger = LogManager.getLogger(this.getClass());
		logger.info("*** Starting of TS_06_GiftCardCustomization ***");
		try{
			giftCards = new GiftCardsPage(driver);
			boolean isGiftCardsButtonVisible = giftCards.isGiftCardsButtonVisible();
			Assert.assertEquals(isGiftCardsButtonVisible, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 1,groups= {"Smoke Test","Master"})
	public void tc_017_navigateToGiftCardsPage() {
		try {
			giftCards.clickGiftCardsButton();
			logger.info(" Gift card button clicked successfully ");
		}
		catch(Exception e) {
			logger.info(" Gift card button clicked failed ");
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 2,groups= {"Regression Test","Master"})
	public void tc_018_verifyBirthdayCardIsDisplayed() {
		try {
			giftCards.scrollToBirthdayOrAnniversaryOptions();
			boolean isBirthdayCardDisplayed = giftCards.isBirthdayOrAnniversaryOptionsDisplayed();
			Assert.assertEquals(isBirthdayCardDisplayed, true);
		}
		catch(Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(priority = 3,groups= {"Regression Test","Master"})
	public void tc_019_giftCardCustomization() {
		try {
			logger.info(" Gift card Customization starts ");
			giftCards.chooseBirthdayOrAnniversaryOption();
			giftCards.selectGiftCardPrice();
			giftCards.selectMonth();
			giftCards.selectDate();
			giftCards.clickNext();
		}
		catch(Exception e) {
			logger.info(" Gift card Customization failed ");
			Assert.fail(e.getMessage());
		}
		logger.info("*** Ending of TS_06_GiftCardCustomization ***");
	}
}
