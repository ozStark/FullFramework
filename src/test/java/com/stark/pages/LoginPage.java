package com.stark.pages;

import com.stark.utilities.ConfigurationReader;
import com.stark.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "inputEmail")
    WebElement usernameBox;
    @FindBy(id = "inputPassword")
    WebElement passwordBox;
    @FindBy(tagName = "button")
    WebElement signInBtn;

    /**
     * any user login with custom credentials
     * @param username
     * @param password
     */
    public void login(String username, String password){
        Driver.getDriver().get(ConfigurationReader.getProperty("libraryURL"));
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        signInBtn.click();
    }

    /**
     * user librarian01 login
     */
    public void librarianLogin(){
        Driver.getDriver().get(ConfigurationReader.getProperty("libraryURL"));
        usernameBox.sendKeys(ConfigurationReader.getProperty("librarianUser01"));
        passwordBox.sendKeys(ConfigurationReader.getProperty("userPasswords"));
        signInBtn.click();
    }

    /**
     * user student01 login
     */
    public void studentLogin(){
        Driver.getDriver().get(ConfigurationReader.getProperty("libraryURL"));
        usernameBox.sendKeys(ConfigurationReader.getProperty("studentUser01"));
        passwordBox.sendKeys(ConfigurationReader.getProperty("userPasswords"));
        signInBtn.click();
    }


}
