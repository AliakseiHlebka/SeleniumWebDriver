package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.Email;
import model.User;
import page.GoogleLoginPage;
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailDraftsPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;
import service.EmailCreator;
import service.ValidUserCreator;
import util.AlertsGenerator;
import util.CurrentUrlReader;

public class GoogleMailSaveNewMailAsDraftAndDeleteTest extends CommonConditions {

    @Test(description = "Create new mail, save it as draft, and then delete it from Drafts folder")
    public void googleMailSaveMailAsDraftTest() {

        User testUser = new ValidUserCreator().createUser();
        Email newEmail = EmailCreator.withAllFieldsFilled();

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);

        Assert.assertEquals(new CurrentUrlReader().getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");

        GoogleMailCreateNewEmailPage createNewEmailPage = homePage.goToCreateNewEmail();
        createNewEmailPage.fillInEmailData(newEmail);
        homePage = createNewEmailPage.closeEmailPopupWindow();
        GoogleMailDraftsPage draftsPage = homePage.openDraftsPage();
        GoogleMailEmailDetailsPage emailDetailsPage = draftsPage.openDraftEmail(newEmail.getEmailSubject());

        Assert.assertEquals(emailDetailsPage.getEmailAddresseeTextField()
                .getText(), newEmail.getEmailAddressee(), "Email addressee is different from the expected one!");
        Assert.assertEquals(emailDetailsPage.getEmailBodyTextField()
                .getText(), newEmail.getEmailBody(), "Email body is different from the expected one!");

        draftsPage = emailDetailsPage.deleteDraftEmailWithHotKeys();

        Assert.assertTrue(draftsPage.getNoDraftEmailsMessage().getText()
                        .contains("Нет сохраненных черновиков."), "Drafts folder is not empty!");

        draftsPage.logoutGoogleAccount();
        new AlertsGenerator().generateTestPassAlert();
    }
}
