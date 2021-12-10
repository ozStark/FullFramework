package com.stark.pages;

import com.stark.utilities.BrowserUtil;
import com.stark.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LibrarianCommonElements {


    public LibrarianCommonElements(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//li[@class='nav-item']/a/span[1]")
    List<WebElement> librarianCommonLinks;
    @FindBy(xpath = "//span[.='Dashboard']")
    WebElement dashboardLink;
    @FindBy(xpath = "//span[.='Books']")
    WebElement booksLink;
    @FindBy(xpath = "//span[.='Users']")
    WebElement usersLink;

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);

    /**
     * navigate to given common page
     * @param pageName
     */
    public void navigateTo(String pageName) {
        switch (pageName){
            case "Books":
                wait.until(ExpectedConditions.visibilityOf(booksLink));
                booksLink.click();
                break;
            case "Users":
                wait.until(ExpectedConditions.visibilityOf(usersLink));
                usersLink.click();
                break;
            case "Dashboard":
                wait.until(ExpectedConditions.visibilityOf(dashboardLink));
                dashboardLink.click();
                break;
        }
    }


    public static void main(String[] args) {

        LoginPage loginPage = new LoginPage();
        loginPage.librarianLogin();

        LibrarianCommonElements librarianCommonElements = new LibrarianCommonElements();
        librarianCommonElements.navigateTo("Books");
        BrowserUtil.waitFor(3);

        Driver.getDriver().quit();
    }





}
