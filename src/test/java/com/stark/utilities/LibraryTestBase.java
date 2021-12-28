package com.stark.utilities;

import io.cucumber.java.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public class LibraryTestBase {

    //@BeforeAll
    @BeforeAll
    public static void setup(){
       baseURI = ConfigurationReader.getProperty("libraryURL");
        basePath = ConfigurationReader.getProperty("libraryBasePath");

//        baseURI= "https://library2.cybertekschool.com/";
//        basePath= "/rest/v1";

//        String url = ConfigurationReader.getProperty("DBURL");
//        String usrnm = ConfigurationReader.getProperty("DBusername");
//        String pswrd = ConfigurationReader.getProperty("DBpassword");
//
//        DB_Util.createConnection(url,usrnm,pswrd);

    }

    @AfterAll
    public static void tearDown() {
        reset();
        DB_Util.destroy();
    }

}
