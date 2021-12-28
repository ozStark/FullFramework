package com.stark.step_definitions;

import com.stark.pages.LibrarianBooksPage;
import com.stark.pages.LibrarianCommonElements;
import com.stark.pages.LoginPage;
import com.stark.utilities.BrowserUtil;
import com.stark.utilities.ConfigurationReader;
import com.stark.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddBookAndVerifyDBStepDef {


    @Given("the librarian is at the {string} page")
    public void the_librarian_is_at_the_page(String page) {
        LoginPage loginPage = new LoginPage();
        loginPage.librarianLogin();
        LibrarianCommonElements librarianCommonElements = new LibrarianCommonElements();
        librarianCommonElements.navigateTo(page);
    }

    @When("the librarian adds a book")
    public void the_librarian_adds_a_book() {
        LibrarianBooksPage librarianBooksPage = new LibrarianBooksPage();
        librarianBooksPage.addRandomBook();
        BrowserUtil.waitFor(5);
    }

    @Then("the DB name author year and isbn should match")
    public void the_db_name_author_year_and_isbn_should_match() {
        LibrarianBooksPage librarianBooksPage = new LibrarianBooksPage();
        String bookName = librarianBooksPage.getCreatedBookName();
        String authorName = librarianBooksPage.getCreatedBookAuthor();
        String ISBN = librarianBooksPage.getCreatedBookISBN();
        String year = librarianBooksPage.getCreatedBookYear();
        DB_Util.createConnection();

        String query = "SELECT name, author, year, isbn FROM books WHERE " +
            "name = \'" + bookName + "\' AND " +
            "author = \'" + authorName +   "\' AND " +
            "year = \'" + year + "\' AND " +
            "isbn = \'" + ISBN + "\'"  ;
        DB_Util.runQuery(query);
        List<String> DB_BookInfo = DB_Util.getRowDataAsList(1);
        List<String> createdBookInfo = new ArrayList<>();
        createdBookInfo.add(bookName);
        createdBookInfo.add(authorName);
        createdBookInfo.add(year);
        createdBookInfo.add(ISBN);
        assertEquals(createdBookInfo, DB_BookInfo);
    }


}
