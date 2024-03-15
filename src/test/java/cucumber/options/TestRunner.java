package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", 
plugin = {"json:target/jsonReports/cucumber-report.json","pretty","html:target/cucumber.html"}, 
glue = {"stepDefinations" }
/* tags= ("@AddPlace") */)

public class TestRunner {

}
