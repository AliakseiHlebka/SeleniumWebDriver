package test;

import model.Email;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.GoogleLoginPage;
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailHomePage;
import page.GoogleMailSentEmailsPage;
import service.EmailCreator;
import service.UserCreator;

public class GoogleMailSendEmailTest extends CommonConditions {

    @Test(description = "Create a new mail, save it as a draft, and send to the recipient")
    public void googleMailSendEmailTest() {

        User testUser = UserCreator.withValidCredentials();
        Email newEmail = EmailCreator.withAllFieldsFilled();

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);

        Assert.assertEquals(driver.getCurrentUrl(), homePage.getHomepageUrl(), "Login failed");

        GoogleMailCreateNewEmailPage createNewEmailPage = homePage.goToCreateNewEmail();
        createNewEmailPage.createNewEmail(newEmail);
        homePage = createNewEmailPage.sendEmail();
        GoogleMailSentEmailsPage sentEmailsPage = homePage.openSentEmailsPage();
        createNewEmailPage = sentEmailsPage.openSentEmail(newEmail.getEmailSubject());

        Assert.assertEquals(createNewEmailPage.getEmailSubject()
                .getText(), newEmail.getEmailSubject(), "Email subject is different from the expected one!");
        Assert.assertEquals(createNewEmailPage.getEmailBody()
                .getText(), newEmail.getEmailBody(), "Email body is different from the expected one!");

        homePage.logoutGoogleAccount();
    }
}