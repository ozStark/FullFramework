package com.stark.runner;

// This class has only one purpose
// Instructing how and what feature we want to run
// features = "src/test/resources/features"
// where are the step definitions
//  glue = "com/cydeo/step_definitions"
// do we want to just generate missing step definitions
//dryRun=true will run the test without running all scenario for missing steps
// so you can copy all the missing steps if there is any
// it's like a quick scan of all your feature steps has step definitions or not

// do we want to get json , html report
// do we want to filter the test run according to certain tags

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(  features = "src/test/resources/features" , //alternatively "classpath:features"
        glue = "com/stark/step_definitions" ,
        publish = true, // it will give you public link of your local html report
        plugin = {"pretty", "html:target/cucumber.html" ,
                "rerun:target/rerun.txt" ,  // store the failed scenario into rerun.txt
                "me.jvt.cucumber.report.PrettyReports:target",  // fancy report
                "json:target/cucumber.json"
        }


        ,tags = ""
        ,dryRun = false



)

public class TestRunner {
}

