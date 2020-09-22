package practice.AppiumFramework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	
	
	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public AppiumDriverLocalService startServer() {
		
		boolean Flag=checkIfServerIsRunnning(4723);
		
		if(!Flag) {
		service=AppiumDriverLocalService.buildDefaultService();
		//adding condition if server is already started.
		service.start();
		}
		return service;
		
	}
	
	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
		serverSocket = new ServerSocket(port);

		serverSocket.close();
		} catch (IOException e) {
		//If control comes here, then it means that the port is in use
		isServerRunning = true;
		} finally {
		serverSocket = null;
		}
		return isServerRunning;
		}

	public static AndroidDriver<AndroidElement> Capabilities(String AppName) throws MalformedURLException, Throwable {
		// TODO Auto-generated method stub

		System.getProperty("user.dir");
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/practice/AppiumFramework/global.properties");
		//FileInputStream fis=new FileInputStream("/home/amolak/eclipse_workspace_cucumber/AppiumFramework/src/main/java/practice/AppiumFramework/global.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		
		File f = new File("src");
		//File Fs= new File(f,"ApiDemos-debug.apk");
		File Fs= new File(f,(String)prop.getProperty(AppName));
		
		String Device=(String)prop.getProperty("device_Name");
		
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, Device);
		cap.setCapability(MobileCapabilityType.APP, Fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
		cap.setCapability("chromedriverExecutable", "/home/amolak/chromedriverM");
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		//AndroidDriver<AndroidElement> driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		
		return driver;
		
	}
	//screenshot logic.
	public static void getScreenshot(String s) throws IOException {
		
	File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrfile,new File("/home/amolak/eclipse_workspace_cucumber/AppiumFramework/Screenshot/"+s+".png"));
		
		
	}

}
