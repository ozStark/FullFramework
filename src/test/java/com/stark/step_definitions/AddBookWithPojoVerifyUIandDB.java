package com.stark.step_definitions;

import com.github.javafaker.Faker;
import com.stark.pages.LibrarianCommonElements;
import com.stark.pages.LoginPage;
import com.stark.pojo.Book;
import com.stark.utilities.LibraryAPIUtil;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class AddBookWithPojoVerifyUIandDB { //extends LibraryTestBase

    Faker f = new Faker();

    String token;

    String name ;
    String isbn ;
    String year ;
    String author ;
    Object category ;
    String description ;

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




        name = f.book().title();
        isbn = f.numerify("############");
        year = f.numerify("19##");
        author = f.book().author();
        category = f.number().numberBetween(1,20);
        description = f.chuckNorris().fact();

        Book book = new Book(
                name,
                isbn,
                year,
                author,
                category,
                description);


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
