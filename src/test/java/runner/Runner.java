package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports",
                "json:target/cucumber.json"
        },
        features = "src/test/resources/feature",
        glue = "steps",
        tags = "@getAllUsers",
        dryRun = false
//        publish = true
)
public class Runner {

}
