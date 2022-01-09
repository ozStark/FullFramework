package com.stark.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.URL;
import java.util.Objects;

/**
 * We wanted to have a class with that only return Single object
 * no matter how many time you asked for object
 * so we are creating this class with technic we learned from Singleton pattern
 */
public class Driver {
    public static void main(String[] args) {
        Driver.getDriver();
        System.out.println((System.getProperty("BROWSER")));
    }
    //
    private static WebDriver obj;

    private Driver() {
    }

    public static WebDriver getDriver() {
        // read the browser type you want to launch from properties file
        String browserName = ConfigurationReader.getProperty("browser");
        //if system property BROWSER not null, use it instead of predefined
        // browser type in properties file
        if (Objects.nonNull(System.getProperty("BROWSER"))) {
            browserName = System.getProperty("BROWSER");
            System.out.println("browser type was adjusted");
        }
        System.out.println("Browser type: " + browserName);
        if (obj == null) {
            // according to browser type set up driver correctly
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;
                case "remote-chrome":
                    try {
                        // assign your grid server address
                        String gridAddress = "54.235.53.73";
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        obj = new RemoteWebDriver(url, desiredCapabilities);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("failed to create remote session.");
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;
                // other browsers omitted
                default:
                    obj = null;
                    System.out.println("UNKNOWN BROWSER TYPE!!! " + browserName);
            }
            return obj;


        } else {
//            System.out.println("You have it just use existing one");
            return obj;

        }

    }

    /**
     * Quitting the browser and setting the value of
     * WebDriver instance to null because you can re-use already quitted driver
     */
    public static void closeBrowser() {

        // check if we have WebDriver instance or not
        // basically checking if obj is null or not
        // if not null
        // quit the browser
        // make it null , because once quit it can not be used
        if (obj != null) {
            obj.quit();
            // so when ask for it again , it gives us not quited fresh driver
            obj = null;
        }

    }
}
