package com.stark.pages;

import com.github.javafaker.Faker;
import com.stark.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        this.createdBookISBN= faker.code().isbn10();
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
}