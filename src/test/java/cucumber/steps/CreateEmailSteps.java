package cucumber.steps;

import org.testng.Assert;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import driver.DriverSingleton;
import model.Email;
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailHomePage;

public class CreateEmailSteps {

    private GoogleMailHomePage homepage = new GoogleMailHomePage(DriverSingleton.getDriver());
    private GoogleMailCreateNewEmailPage createNewEmailPage;

    @When("the user opens Create New Email popup")
    public void openCreateNewEmailPopup() {
        createNewEmailPage = homepage.goToCreateNewEmail();
    }

    @When("the user sets Email Addressee = {string}")
    public void enterEmailAddressee(String addressee) {
        createNewEmailPage.enterEmailAddressee(addressee);
    }

    @When("the user sets Email Subject = {string}")
    public void enterEmailSubject(String subject) {
        createNewEmailPage.enterEmailSubject(subject);
    }

    @When("the user sets Email Body$")
    public void enterMultiLineEmailBody(String body) {
        createNewEmailPage.enterEmailBody(body);
    }

    @When("the user sends the email")
    public void sendEmail() {
        createNewEmailPage.sendEmail();
    }

    @When("the user clicks Close New Email button")
    public void clickCloseNewEmailButton() {
        createNewEmailPage.closeEmailPopupWindow();
    }

    @Then("confirmation message {string} is displayed")
    public void isConfirmationMessageDisplayed(String message) {
        Assert.assertTrue(createNewEmailPage.getActionConfirmationPopup().getText()
        .contains(message), "Email was not sent");
    }

    @When("the user fills in email$")
    public void fillInEmailData(DataTable dataTable) {
        List<String> emailFields = dataTable.asList();
        Email email = new Email(emailFields.get(0), emailFields.get(1), emailFields.get(2));
        createNewEmailPage.fillInEmailData(email);
    }
}
