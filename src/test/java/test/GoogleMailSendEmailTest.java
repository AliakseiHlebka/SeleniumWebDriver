package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.Email;
import model.User;
import page.GoogleLoginPage;
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailHomePage;
import page.GoogleMailSentEmailsPage;
import service.EmailCreator;
import service.ValidUserCreator;
import util.AlertsGenerator;

public class GoogleMailSendEmailTest extends CommonConditions {

    @Test(description = "Create a new mail, save it as a draft, and send to the recipient")
    public void googleMailSendEmailTest() {

        User testUser = new ValidUserCreator().createUser();
        Email newEmail = EmailCreator.withAllFieldsFilled();

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);

        Assert.assertEquals(driver.getCurrentUrl(), homePage.getHomepageUrl(), "Login failed");

        GoogleMailCreateNewEmailPage createNewEmailPage = homePage.goToCreateNewEmail();
        createNewEmailPage.fillInEmailData(newEmail);
        homePage = createNewEmailPage.sendEmail();
        GoogleMailSentEmailsPage sentEmailsPage = homePage.openSentEmailsPage();
        createNewEmailPage = sentEmailsPage.openSentEmail(newEmail.getEmailSubject());

        Assert.assertEquals(createNewEmailPage.getEmailSubject()
                .getText(), newEmail.getEmailSubject(), "Email subject is different from the expected one!");
        Assert.assertEquals(createNewEmailPage.getEmailBody()
                .getText(), newEmail.getEmailBody(), "Email body is different from the expected one!");

        createNewEmailPage.logoutGoogleAccount();
        new AlertsGenerator().generateTestPassAlert();
    }
}