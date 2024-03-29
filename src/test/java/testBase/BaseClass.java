package testBase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import utilities.PropertyReader;


public class BaseClass {
	public static WebDriver driver;
	public PropertyReader readProperty = new PropertyReader();
	public static Logger logger;
	
	@BeforeTest(groups= {"Smoke Test","Sanity Test","Regression Test","Master"})
	@Parameters({"Browser", "OS"})
	public void setup(String browser, String OS) throws IOException {
		System.out.println("No matching browser found");
		readProperty.propertyReader();
		
		if(readProperty.readExecutionEnvironment().equalsIgnoreCase("local")){
			switch(browser.toLowerCase()) {
				case "chrome":{
					driver = new ChromeDriver();
					break;
				}
				case "edge":{
					driver = new EdgeDriver();
					break;
				}
				default: {
					System.out.println("No matching browser found");
					return;
				}
			}
		}
		else if(readProperty.readExecutionEnvironment().equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if(OS.equalsIgnoreCase("Windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(OS.equalsIgnoreCase("Mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("No matching OS");
				return;
			}
			switch(browser.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome");
				break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge");
				break;
				default: System.out.println("No matching browser");
				return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
//		logger = LogManager.getLogger(this.getClass());
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));

		driver.navigate().to(readProperty.readURL());
		driver.manage().window().maximize();
	}
	
	@AfterTest(groups= {"Smoke Test","Sanity Test","Regression Test","Master"})
	public void teardown() {
		driver.quit();
	}
	
	public String captureScreen(String name) throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
}
