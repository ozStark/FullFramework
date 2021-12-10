package com.stark.pages;

import com.stark.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LibrarianDashboardPage {

    public LibrarianDashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "user_count")
    WebElement userCount;
    @FindBy(id = "book_count")
    WebElement bookCount;
    @FindBy(id = "borrowed_books")
    WebElement borrowedBookCount;



    public int getUserCountAsInt(){
        return Integer.parseInt(userCount.getText());
    }
    public String getUserCountAsString(){
        return userCount.getText();
    }

    public int getBookCountAsInt(){
        return Integer.parseInt(bookCount.getText());
    }
    public String getBookCountAsString(){
        return bookCount.getText();
    }

    public int getBorrowedBookCountAsInt(){
        return Integer.parseInt(borrowedBookCount.getText());
    }
    public String getBorrowedBookCountAsString(){
        return borrowedBookCount.getText();
    }



}
