package practice.AppiumFramework;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.FormPage;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;



import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

public class Ecommerence_app_TC1 extends Base  {

		@Test
		public void ECommFlow() throws Throwable, Throwable {
		//service=startServer();
		AndroidDriver<AndroidElement> driver=Capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    FormPage p1=new FormPage(driver);
		
	    p1.nameField.sendKeys("HELLO");
		//driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("HELLO");
		driver.hideKeyboard();
		p1.Female_Radio_button.click();
		//driver.findElementByXPath("//*[@text='Female']").click();
		
		driver.findElementById("android:id/text1").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Argentina\").instance(0))").click();
		//driver.findElementByXPath("//*[@text='Argentina']").click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
		//String msg=driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");		
		//System.out.println("Toast Msg :"+msg);
		//sERACHING AND ADDING PRODUCT OT THE CARD.
		//driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoViewnew UiSelector().textMatches(\"PG 3\").instance(0))"));
		
      driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"PG 3\").instance(0))"));
	
      
      int count= driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
      
      for(int i=0;i<count;i++) {
    	  
    	  String product_text=driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
    	  
    	  if(product_text.equalsIgnoreCase("PG 3")) {
    		  
    		  driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
    		  break;
    	  }
    	  
      }
      driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		/*
		 * String Finla_prouct=driver.findElement(By.id(
		 * "com.androidsample.generalstore:id/productName")).getText();
		 * 
		 * 
		 * if(Finla_prouct.equalsIgnoreCase("PG 3")) {
		 * System.out.println("Correct Product. "); }
		 */
    
		
		  WebElement CHECKBOX=driver.findElement(By.className("android.widget.CheckBox"));
		  TouchAction t= new TouchAction(driver);
		  t.tap(tapOptions().withElement(element(CHECKBOX))).perform();
		  
		  WebElement terms=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		  t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(2))).release().perform();
		  driver.findElement(By.id("android:id/button1")).click();
		  
		  //Thread.sleep(3000);
		  driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		  Thread.sleep(5000);
		  //aDD LOGIC TO SWITCH TO WEBVIEW.
		  Set<String> context=driver.getContextHandles();
		  for(String name :context) {
			  System.out.println("Context Present: "+name);
		  }
		  
		  driver.context("WEBVIEW_com.androidsample.generalstore");
		 driver.findElement(By.name("q")).sendKeys("time");
		 driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		 driver.pressKey(new KeyEvent(AndroidKey.BACK));
		 driver.context("NATIVE_APP");
		// service.stop();
		  
		 
  	
	}

	
	
	
	
}

