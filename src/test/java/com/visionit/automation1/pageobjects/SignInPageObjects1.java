package com.visionit.automation1.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.visionit.automation1.pageobjects.SignInPageObjects1;

public class SignInPageObjects1 {
	private static final Logger logger = LogManager.getLogger(SignInPageObjects1.class);

    private WebDriver driver;

    private By input_textbox_email  = By.id("ap_email");

    //Section 3: Parameterize the constructor
    public SignInPageObjects1(WebDriver driver){
        this.driver = driver;
    }

    public void validateEmailInputTextBoxIsDisplayed(){
        if (driver.findElement(input_textbox_email).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Email input box is displayed");
        }else{
            logger.fatal("Email input box not displayed");
            Assert.fail("Email text box does not appear for login after clicking on Sign in button");
        }
    }

    public void enterTextInEmailTextBox(String text){
        logger.info("Text entered in email id: " + text);
        driver.findElement(input_textbox_email).sendKeys(text);
    }

}



