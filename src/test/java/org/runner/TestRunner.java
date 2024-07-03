package org.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Testing\\APICucumber\\src\\test\\resources\\Swagger.feature",
glue="org.steps")
public class TestRunner {

}
