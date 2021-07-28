package cucumber.steps;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import driver.DriverSingleton;
import model.User;
import page.GoogleLoginPage;
import page.GoogleMailHomePage;
import service.ValidIUserCreator;
import util.CurrentUrlReader;

public class LoginSteps {

    private User testUser;
    private GoogleMailHomePage homePage;

    @Given("there is a user with valid credentials")
    public void createUserWithValidCredentials() {
        testUser = new ValidIUserCreator().createUser();
    }

    @When("the user logs in to Google Mail account")
    public void logInToGoogleMailAccount() {
        homePage = new GoogleLoginPage(DriverSingleton.getDriver()).loginToGoogleMail(testUser);
    }

    @Then("Home Page is opened")
    public void verifyHomePageUrl() {
        Assert.assertEquals(new CurrentUrlReader().getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");
    }
}
