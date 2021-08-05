package cucumber.steps;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import driver.DriverSingleton;
import page.GoogleMailDraftsPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;

public class DraftsPageSteps {

    private GoogleMailHomePage homePage = new GoogleMailHomePage(DriverSingleton.getDriver());
    private GoogleMailDraftsPage draftsPage;
    private GoogleMailEmailDetailsPage emailDetailsPage;

    @When("the user opens Drafts page")
    public void openDraftsPage() {
        draftsPage = homePage.openDraftsPage();
    }

    @When("the user opens Draft email with Subject = {string}")
    public void opensDraftEmail(String subject) {
        emailDetailsPage = draftsPage.openDraftEmail(subject);
    }

    @When("the user deletes draft email")
    public void deleteDraftEmail() {
        emailDetailsPage.deleteDraftEmail();
    }

    @Then("Drafts page contains {string} header")
    public void draftsPageContainsHeader(String header) {
        Assert.assertTrue(draftsPage.getNoDraftEmailsMessage().getText()
            .contains(header), "Drafts folder is not empty!");
    }
}
