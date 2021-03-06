package com.visionit.automation1.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.visionit.automation1.stepdefs.StepDefs1_WithOutPageObjectModel;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefs1_WithOutPageObjectModel {
	
	private static final Logger logger = LogManager.getLogger(StepDefs1_WithOutPageObjectModel.class);
	
	WebDriver driver;
    String base_url = "https://amazon.in";
    int implicit_wait_timeout_in_sec = 20;
    Scenario scn; // this is set in the @Before method
    
 // make sure to use this before import io.cucumber.java.Before;
    // Use @Before to execute steps to be executed before each scenario
    // one example can be to invoke the browser
  //Scenario(see below method arg type) is a Interface, given by Cucumber;
    //This object is 'Injected' at run time and can be used for logging, screen shot attachment to reports
    //Other than that it also carries steps and scenario pass, fail status(more on this later)
    
    //@Before
    public void setUp(Scenario scn){
    	this.scn=scn;//Assign this to class variable, so that it can be used in all the step def methods
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
        scn.log("Browser invoked");
        logger.info("Browser invoked.");
    }


//	@Given("User opened browser")
	public void user_opened_browser() {
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	    
	}
    
 // make sure to use this after import io.cucumber.java.After;
    // Use @After to execute steps to be executed after each scenario
    // one example can be to close the browser
    //@After
    public void cleanUp(){
        driver.quit();
        scn.log("Browser Closed");
        logger.info("Browser Closed");
    }


//	@Given("User navigated to the home application url")
	public void user_navigated_to_the_home_application_url() {
		driver.get(base_url);
		scn.log("Browser navigated to URL: " + base_url);
		logger.info("Browser navigated to URL: " + base_url);
        String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
        String actual =driver.getTitle();
        Assert.assertEquals("Page Title validation",expected,actual);
        scn.log("Page title validation successfull. Actual title: " + actual );
        logger.info("Page title validation successfull. Actual title: " + actual);
        
	}
	
	//@When("User Search for product {string}")
	public void user_search_for_product(String productName) {
		//Wait and Search for product
        WebDriverWait webDriverWait = new WebDriverWait(driver,20);
        WebElement elementSearchBox = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

        elementSearchBox.sendKeys(productName);
        scn.log("Product Searched: " + productName);
        logger.info("Product Searched: " + productName);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        scn.log("Clicked on the GO button");
        logger.info("Clicked on the GO button");
	}
	//@Then("Search Result page is displayed")
	public void search_result_page_is_displayed() {
		 //Wait for title
        WebDriverWait webDriverWait1 = new WebDriverWait(driver,20);
        webDriverWait1.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));

        //Assertion for Page Title
        Assert.assertEquals("Page Title validation","Amazon.in : Laptop", driver.getTitle());
        scn.log("Page title validation successfull: " + driver.getTitle());
        logger.info("Page title validation successfull: " + driver.getTitle());
	}
	
//	@Then("Close the browser")
//	public void close_the_browser() {
//	    driver.quit();
//	    
//	}
	
	//@When("User click on any product")
	public void user_click_on_any_product() {
		//listOfProducts will have all the links displayed in the search box
        List<WebElement> listOfProducts = driver.findElements(By.xpath("//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal']"));
        scn.log("Number of products searched: " + listOfProducts.size());
        logger.info("Number of products searched: " + listOfProducts.size());
        //But as this step asks click on any link, we can choose to click on Index 0 of the list
        listOfProducts.get(0).click();
        scn.log("Click on the first Link in the List. Link Text: " + listOfProducts.get(0).getText());
        logger.info("Click on the first Link in the List. Link Text: " + listOfProducts.get(0).getText());
	    
	}

	//@Then("Product Description is displayed in new tab")
	public void product_description_is_displayed_in_new_tab() {
		//As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
        Set<String> handles = driver.getWindowHandles(); // get all the open windows
        scn.log("List of windows found: "+handles.size());
        logger.info("List of windows found: "+handles.size());
        scn.log("Windows handles: " + handles.toString());
        logger.info("Windows handles: " + handles.toString());
        Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
        String original = it.next();//gives the parent window id
        String prodDescp = it.next();//gives the child window id

        driver.switchTo().window(prodDescp); // switch to product Descp
        scn.log("Switched to the new window/tab");
        logger.info("Switched to the new window/tab");
        
        //Now driver can access new driver window, but can not access the original tab
        //Check product title is displayed
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        Assert.assertEquals("Add to Cart Button",true,addToCartButton.isDisplayed());
        scn.log("Add to cart Button is displayed");
        logger.info("Add to cart Button is displayed");
        
        //Switch back to the Original Window, however no other operation to be done
        driver.switchTo().window(original);
        scn.log("Switched back to Original tab");
        logger.info("Switched back to Original tab");

	    
	}




}


