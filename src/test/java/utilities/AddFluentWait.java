package utilities;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class AddFluentWait {
	FluentWait<WebDriver> wait;
	
	public void waitForPopupToOpen(WebDriver driver) {
		wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(25));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class = 'close-reveal-modal hide-mobile']")));
	}
	
	public void waitForPriceFilterToOpen(WebDriver driver) {
		wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(25));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'filter-name' and text() = 'Price']")));
	}
	
	public void waitForStorageFilterToOpen(WebDriver driver) {
		wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(25));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class = 'filter-name' and text() = 'Storage Type']")));
	}
	
	public void waitForLightingAndDecorMenuToOpen(WebDriver driver) {
		wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Duration.ofSeconds(25));
		wait.pollingEvery(Duration.ofSeconds(5));
		wait.ignoring(NoSuchElementException.class);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class = 'inline-list left']/li[5]//a[text() = 'Home Decor']")));
	}
}