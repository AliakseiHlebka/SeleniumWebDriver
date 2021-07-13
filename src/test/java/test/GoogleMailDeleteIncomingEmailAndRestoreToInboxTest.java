package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.GoogleLoginPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;
import page.GoogleMailTrashBinPage;
import service.UserCreator;

public class GoogleMailDeleteIncomingEmailAndRestoreToInboxTest extends CommonConditions {

    @Test(description = "Delete incoming email and restore it back to Inbox from Trash Bin")
    public void googleMailDeleteIncomingEmailAndRestoreToInboxTest() {

        String deleteEmailTargetMessage = "Цепочка помещена в корзину";
        String restoreEmailTargetMessage = "Цепочка перемещена во входящие.";
        String emailSender = "The Google team";

        User testUser = UserCreator.withValidCredentials();
        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);
        GoogleMailEmailDetailsPage emailDetailsPage = homePage.openInboxEmail(emailSender);
        homePage = emailDetailsPage.deleteEmail();

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(deleteEmailTargetMessage), "Email was not moved to trash bin!");

        GoogleMailTrashBinPage trashBinPage = homePage.openTrashBinPage();
        emailDetailsPage = trashBinPage.openDeletedEmail(emailSender);
        homePage = emailDetailsPage.moveEmailFromTrashBinToInbox();

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(restoreEmailTargetMessage), "Email was not moved to inbox!");

        homePage.logoutGoogleAccount();
    }
}
