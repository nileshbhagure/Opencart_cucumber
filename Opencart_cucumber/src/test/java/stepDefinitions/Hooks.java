package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/*In Junit we have annoation method called hook
  4 annotion we have 
   @Before : will execute before all the step defn files
   @After :  will execute after executing all the step defn files
   @AfterStep : will execute after completion of every step defn step
   @BeforeStep 
whenever we implement step defn there are common steps we need to execute, so those common step we need to keep in hook.java
This method no need to call. it will be automatically triggered when we execute test runner class
*/

public class Hooks {

	 WebDriver driver;
	 Properties p;
     
	@Before
    public void setup() throws IOException
    {
    	driver=BaseClass.initilizeBrowser();
    	    	
    	p=BaseClass.getProperties();
    	driver.get(p.getProperty("appURL"));
    	driver.manage().window().maximize();
    
    			
	}
		
    
    @After
    public void tearDown(Scenario scenario) {
        		
       driver.quit();
       
    }
    
    //if scenario is failed then it will be executed
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        
    	// this is for cucumber junit report
        if(scenario.isFailed()) {
        	
        	TakesScreenshot ts=(TakesScreenshot) driver;
        	byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
        	scenario.attach(screenshot, "image/png",scenario.getName());   //will attach report to directly to cucumber report
        	            
        }
      
    }
   
}
