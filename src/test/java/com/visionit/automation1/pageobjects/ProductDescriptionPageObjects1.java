package com.visionit.automation1.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDescriptionPageObjects1 {

    private static final Logger logger = LogManager.getLogger(ProductDescriptionPageObjects1.class);

    private WebDriver driver;

    private By product_title = By.id("productTitle");
    private By add_to_cart_button = By.id("add-to-cart-button");

    //Section 3: Paratmerize the constuctor
    public ProductDescriptionPageObjects1(WebDriver driver){
        this.driver = driver;
    }

    public void ValidateProductTileIsCorrectlyDisplayed(){
        if (driver.findElement(product_title).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Product Title is displayed");
        }else{
            logger.fatal("Product Title is not displayed");
            Assert.fail("Product Title is not displayed");
        }
    }

    public void ValidateAddToCartButtonIsCorrectlyDisplayed(){
        if (driver.findElement(add_to_cart_button).isDisplayed()){
            Assert.assertTrue(true);
            logger.info("Add to Cart Button is displayed");
        }else{
            logger.fatal("Add to Cart Button is not displayed");
            Assert.fail("Add to Cart Button is not displayed");
        }
    }


}



