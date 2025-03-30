package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = {"src/test/resources/features/"},
        glue = {"StepDefinitionsFiles"},
       // tags=("@create"),
        dryRun = false,monochrome = true,
        plugin = {"pretty", "html:target/cucumber-html-reports/report.html","json:target/cucumber-html-reports/cucumber.json"})
public class TestCucumberRunner extends AbstractTestNGCucumberTests  {

}
