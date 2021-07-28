package cucumber.steps;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import driver.DriverSingleton;
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailHomePage;
import page.GoogleMailSentEmailsPage;

public class SentEmailsPageSteps {

    GoogleMailHomePage homePage = new GoogleMailHomePage(DriverSingleton.getDriver());
    GoogleMailSentEmailsPage sentEmailsPage;
    GoogleMailCreateNewEmailPage createNewEmailPage;

    @When("the user opens Sent Emails Page")
    public void openSentEmailsPage() {
        sentEmailsPage = homePage.openSentEmailsPage();
    }

    @When("the user opens sent email with subject = {string}")
    public void openSentEmail(String subject) {
        createNewEmailPage = sentEmailsPage.openSentEmail(subject);
    }

    @Then("email body = {string}")
    public void IsEmailBodySame(String body) {
        Assert.assertTrue(createNewEmailPage.getEmailBody()
            .getText().contains(body));
    }
}
