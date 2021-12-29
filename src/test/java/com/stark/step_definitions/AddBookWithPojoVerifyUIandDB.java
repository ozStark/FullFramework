package com.stark.step_definitions;

import com.github.javafaker.Faker;
import com.stark.pages.LibrarianBooksPage;
import com.stark.pages.LibrarianCommonElements;
import com.stark.pages.LoginPage;
import com.stark.pojo.Book;
import com.stark.utilities.LibraryAPIUtil;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AddBookWithPojoVerifyUIandDB { //extends LibraryTestBase

    Faker f = new Faker();

    String token;

    String name ;
    String isbn ;
    String year ;
    String author ;
    String category ;
    String description ;

    List<Object> fromUI ;
    List<String> fromPojo;
    List<Object> test;

    Book book = new Book();
    @Given("The tester is authenticated")
    public void the_tester_is_authenticated() {


//        baseURI= "https://library2.cybertekschool.com/";
//        basePath= "/rest/v1";


        token = LibraryAPIUtil.generateToken();
//                given()
//                        .contentType(ContentType.URLENC)
//                        .formParam("email", "librarian47@library")
//                        .formParam("password", "Sdet2022*")
//                        .when()
//                        .post("/login").path("token");

        System.out.println("Connection Established!");
        System.out.println();
        System.out.println(token);
    }


    @Then("A new book is added using POJO and the add_book endpoint")
    public void a_new_book_is_added_using_pojo_and_the_add_book_endpoint() {

        baseURI= "https://library2.cybertekschool.com/";
        basePath= "/rest/v1";


        String bookCat = String.valueOf(f.number().numberBetween(1,20));

        name = f.book().title();
        isbn = f.numerify("############");
        year = f.numerify("19##");
        author = f.book().author();
        category = bookCat;
        description = f.chuckNorris().fact();


         book = new Book(
                name,
                isbn,
                year,
                author,
                category,
                description);
        System.out.println("@@@@@@@@@@book obj below");
        System.out.println(book);
        System.out.println("vars");
        System.out.println(name + " " + isbn + " " + year);


//        test.add(isbn);
//        test.add(name);
//        test.add(author);
//        test.add(category);
//        test.add(year);



        given()
                .log().all()
                .header("X-LIBRARY-TOKEN", token)
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/add_book")
                .then()
                .log().all()
                .body("message", is("The book has been created."));


    }
    @When("The tester logs in as a student the book should exist")
    public void the_tester_logs_in_as_a_student_the_book_should_exist() {
        LoginPage lp = new LoginPage();

        lp.studentLogin();

        LibrarianCommonElements lbc = new LibrarianCommonElements();

        lbc.navigateTo("Books");

//        System.out.println("********************");
//        System.out.println();
//        System.out.println(author);
//        System.out.println();

        new LibrarianBooksPage().searchQuery(author);

        fromUI = new LibrarianBooksPage().returnFirstRowBookAfterSearch(author);

//        System.out.println("@@@@@@@@@@@@@@@@@@@");
//        System.out.println(fromUI);
//        System.out.println("pojo below");
//        System.out.println(book);

        String s = new LibrarianBooksPage().categoryNameFromId("1");
        System.out.println("SHOULD BE BOOK CAT ABOVE");
        System.out.println();
        System.out.println();

        String isbnFromPojo = book.getIsbn();
        System.out.println("isbnFromPojo = " + isbnFromPojo);
        Object isbnFromUi = fromUI.get(0);
        System.out.println("isbnFromUi = " + isbnFromUi);
        String nameFromPojo = book.getName();
        System.out.println("nameFromPojo = " + nameFromPojo);
        Object nameFromUi = fromUI.get(1);
        System.out.println("nameFromUi = " + nameFromUi);
        String authorFromPojo = book.getAuthor();
        System.out.println("authorFromPojo = " + authorFromPojo);
        Object authorFromUi = fromUI.get(2);

        System.out.println("authorFromUi = " + authorFromUi);
        String genreFromPojo = new LibrarianBooksPage().categoryNameFromId(book.getBook_category_id());

        System.out.println("genreFromPojo = " + genreFromPojo);
        Object genreFromUi = fromUI.get(3);
        System.out.println("genreFromUi = " + genreFromUi);
        Object yearFromPojo = book.getYear();
        System.out.println("yearFromPojo = " + yearFromPojo);
        Object yearFromUi = fromUI.get(4);
        System.out.println("yearFromUi = " + yearFromUi);

        Assertions.assertEquals(isbnFromPojo,isbnFromUi);
        Assertions.assertEquals(nameFromPojo,nameFromUi);
        Assertions.assertEquals(authorFromPojo,authorFromUi);
        Assertions.assertEquals(genreFromPojo,genreFromUi);
        Assertions.assertEquals(yearFromPojo,yearFromUi);

//        System.out.println();
//        System.out.println();
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println(fromUI.get(0)+" zero");
//        System.out.println(fromUI.get(2)+" two");
//        System.out.println(fromUI.get(3)+" three");
//        System.out.println(fromUI.get(4)+" four");
//        System.out.println(fromUI.get(5)+" five");





    }
    @When("The tester queries the database for the book it should exist")
    public void the_tester_queries_the_database_for_the_book_it_should_exist() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("Assert all fields match")
    public void assert_all_fields_match() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
