package com.stark.step_definitions;

import com.stark.utilities.ConfigurationReader;
import com.stark.utilities.DB_Util;
import com.stark.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;

public class Hooks {

    //UI Hooks
    @Before("@ui")
    public void setupDriver(){
        System.out.println("THIS IS FROM @Before inside hooks class");
        // set up implicit wait or all the browser related set up
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        // maximize browser here if you wanted
        Driver.getDriver().manage().window().maximize();
    }

    @After("@ui")
    public void tearDown(Scenario scenario){

        // check if scenario failed or not
        if(scenario.isFailed() ){
            // this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png",scenario.getName());

        }

        System.out.println("THIS IS FROM @After inside hooks class");
        Driver.closeBrowser();

    }


    //DB Hooks
    @Before("@db")
    public void dbHook() {
        System.out.println("creating database connection");
        DB_Util.createConnection(ConfigurationReader.getProperty("library2.db.url")
                , ConfigurationReader.getProperty("library2.db.username")
                , ConfigurationReader.getProperty("library2.db.password"));
    }

    @After("@db")
    public void afterDbHook() {
        System.out.println("closing database connection");
        DB_Util.destroy();

    }


    //API Hooks
    @Before("@api")
    public void setup(){
        baseURI= "https://library2.cybertekschool.com/";
        basePath= "/rest/v1";


    }
    @After("@api")
    public static void tearDown() {
        reset();
    }

}
