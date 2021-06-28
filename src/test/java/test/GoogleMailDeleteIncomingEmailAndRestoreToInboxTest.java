package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        final String GOOGLE_MAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
        String login = "tyrmandyr1@gmail.com";
        String password = "@Tyrmandyr1!";
        String deleteEmailTargetMessage = "Цепочка помещена в корзину";
        String restoreEmailtargetMessage = "Цепочка перемещена во входящие.";
        String emailSender = "The Google team";

        new GoogleLoginPage(driver).loginToGoogleMail(login, password);
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches(GOOGLE_MAIL_URL));

        GoogleMailHomePage homePage = new GoogleMailHomePage(driver);
        GoogleMailEmailDetailsPage emailDetailsPage = new GoogleMailEmailDetailsPage(driver);
        homePage.openInboxEmail(emailSender);
        emailDetailsPage.deleteEmail();

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(deleteEmailTargetMessage), "Email was not moved to trash bin!");

        homePage.openTrashBinPage();
        new GoogleMailTrashBinPage(driver).openDeletedEmail();
        emailDetailsPage.moveEmailFromTrashBinToInbox();

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(restoreEmailtargetMessage), "Email was not moved to inbox!");

        homePage.quitGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
