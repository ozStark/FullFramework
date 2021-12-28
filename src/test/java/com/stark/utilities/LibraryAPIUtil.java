package com.stark.utilities;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class LibraryAPIUtil {

    public static String generateToken(){
        String librarian = ConfigurationReader.getProperty("librarianUser01");
        String password = ConfigurationReader.getProperty("userPasswords");

//        baseURI = ConfigurationReader.getProperty("libraryURL");
//        basePath = ConfigurationReader.getProperty("libraryBasePath");

        return                given()
                        .contentType(ContentType.URLENC)
                        .formParam("email", librarian)
                        .formParam("password", password)
                        .when()
                        .post("/login").path("token");

    }


}
