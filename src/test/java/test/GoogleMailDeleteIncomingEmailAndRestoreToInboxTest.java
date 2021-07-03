package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.GoogleLoginPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;
import page.GoogleMailTrashBinPage;

public class GoogleMailDeleteIncomingEmailAndRestoreToInboxTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test(description = "Delete incoming email and restore it back to Inbox from Trash Bin")
    public void googleMailDeleteIncomingEmailAndRestoreToInboxTest() {

        String login = "tyrmandyr1@gmail.com";
        String password = "@Tyrmandyr1!";
        String deleteEmailTargetMessage = "Цепочка помещена в корзину";
        String restoreEmailtargetMessage = "Цепочка перемещена во входящие.";
        String emailSender = "The Google team";

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(login, password);
        GoogleMailEmailDetailsPage emailDetailsPage = homePage.openInboxEmail(emailSender);
        homePage = emailDetailsPage.deleteEmail();

        Assert.assertEquals(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(deleteEmailTargetMessage), "Email was not moved to trash bin!");

        GoogleMailTrashBinPage trashBinPage = homePage.openTrashBinPage();
        emailDetailsPage = trashBinPage.openDeletedEmail(emailSender);
        homePage = emailDetailsPage.moveEmailFromTrashBinToInbox();

        Assert.assertEquals(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(restoreEmailtargetMessage), "Email was not moved to inbox!");

        homePage.logoutGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
