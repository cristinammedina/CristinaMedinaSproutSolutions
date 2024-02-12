import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Path to your feature files
        glue = "StepDefinitions", // Package where your step definitions are located
        plugin = {"pretty", "html:target/cucumber-reports"} // Plugins for formatting test output
    )
public class TestRunner {

}
