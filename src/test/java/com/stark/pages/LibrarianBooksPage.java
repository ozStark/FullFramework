package com.stark.pages;

import com.github.javafaker.Faker;
import com.stark.utilities.BrowserUtil;
import com.stark.utilities.DB_Util;
import com.stark.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibrarianBooksPage {

    public LibrarianBooksPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[normalize-space(text())='Add Book']")
    public WebElement addBookBtn;
    @FindBy(name = "name")
    WebElement bookName;
    @FindBy(name = "isbn")
    WebElement isbn;
    @FindBy(name = "year")
    WebElement year;
    @FindBy(xpath = "//input[@name='author']")
    WebElement author;
    @FindBy(name = "book_category_id")
    WebElement bookCategoryDropdown;
    @FindBy(id = "description")
    WebElement description;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement saveChanges;
    @FindBy(xpath = "//input")
    WebElement searchBox;


    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
    //ADD BOOK FUNCTIONS

    private static String createdBookName;
    private static String createdBookAuthor;
    private static String createdBookYear;
    private static String createdBookISBN;
    private static String createdBookCategory;


    public void selectRandomBookCategory() {
        Faker faker = new Faker();
        Select bookCategory = new Select(bookCategoryDropdown);
        int categorySize = bookCategory.getOptions().size();
        bookCategory.selectByIndex(faker.number().numberBetween(0, (categorySize - 1)));

    }


    public void addRandomBook() {
        Faker faker = new Faker();
        wait.until(ExpectedConditions.visibilityOf(addBookBtn));
        addBookBtn.click();

        wait.until(ExpectedConditions.visibilityOf(author));
        this.createdBookAuthor = faker.book().author();
        System.out.println("createdBookAuthor = " + this.createdBookAuthor);
        author.sendKeys(createdBookAuthor);

        this.createdBookName = faker.funnyName().name();
        System.out.println("createdBookName = " + this.createdBookName);
        bookName.sendKeys(createdBookName);

        this.createdBookISBN = faker.code().isbn10();
        System.out.println("createdBookISBN = " + createdBookISBN);
        isbn.sendKeys(createdBookISBN);

        this.createdBookYear = "" + faker.number().numberBetween(1900, 2020);
        year.sendKeys(createdBookYear);

        selectRandomBookCategory();

        description.sendKeys("This is sent from addRandomBook Function in LibrarianBooksPage");
        saveChanges.click();
    }


    public String getCreatedBookName() {
        return createdBookName;
    }

    public String getCreatedBookAuthor() {
        return createdBookAuthor;
    }

    public String getCreatedBookYear() {
        return createdBookYear;
    }

    public String getCreatedBookISBN() {
        return createdBookISBN;
    }

    public String getCreatedBookCategory() {
        return createdBookCategory;
    }


    public List<Object> returnFirstRowBookAfterSearch(String searchQuery) {
        List<Object> result = new ArrayList<>();

        // (//tr)[2]/td[2]

        List<WebElement> firstRowData = Driver.getDriver().findElements(By.xpath("(//tr)[2]/td"));

        for (int i = 1; i < firstRowData.size() - 1; i++) {
            result.add(firstRowData.get(i).getText());
        }

        return result;

    }

    public void searchQuery(String searchQuery) {

        this.searchBox.sendKeys(searchQuery);
        BrowserUtil.waitFor(1);

    }

    public String categoryNameFromId(String numberFromPojo) {

        System.out.println("\n this from book page \n");
        System.out.println("numberFromPojo = " + numberFromPojo);
        Map<String, String> fromDb = new HashMap<>();
        DB_Util.runQuery("select id, name from book_categories");
        int intFromPojo = Integer.parseInt(numberFromPojo);
        fromDb = DB_Util.getRowMap(intFromPojo);

        System.out.println("fromDb = " + fromDb);
        System.out.println("fromDb.get(\"name\") = " + fromDb.get("name"));
        System.out.println("\n end of book page function \n");

        return fromDb.get("name");

    }

}
