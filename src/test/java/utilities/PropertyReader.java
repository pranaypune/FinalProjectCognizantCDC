package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	public Properties property;
	
	public void propertyReader() throws IOException {
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		property = new Properties();
		property.load(file);
	}
	
	public String readURL() {
		String appURL = property.getProperty("appURL");
		return appURL;
	}
	
	public String readAddress() {
		String address = property.getProperty("address");
		return address;
	}
	
	public String readPincode() {
		String pinCode = property.getProperty("pincode");
		return pinCode;
	}

	public String readExecutionEnvironment() {
		// TODO Auto-generated method stub
		String env = property.getProperty("environment");
		return env;
	}
}
