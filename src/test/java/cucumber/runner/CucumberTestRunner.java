package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber.html"},
    monochrome = true,
    tags = "@bdd",
    glue = "cucumber",
    features = "classpath:features")

public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
