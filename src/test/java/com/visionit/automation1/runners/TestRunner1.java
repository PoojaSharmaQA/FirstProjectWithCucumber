package com.visionit.automation1.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="classpath:features",//to tell cucumber where is ur feature file
        glue="com.visionit.automation1.stepdefs", // to tell cucumber where is ur step def code
        tags="", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
            "html:target/html/htmlreport.html",
            "json:target/json/file.json",
            },
        monochrome = true, //used to delete unwanted character coming in console,
        publish= true, // used to publish report on cucumber server
        dryRun=false // to tell whether to test run(true) or actual run(false)
         
        )
public class TestRunner1 {
}
