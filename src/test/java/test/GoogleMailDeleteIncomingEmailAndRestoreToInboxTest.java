package test;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.GoogleLoginPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;
import page.GoogleMailTrashBinPage;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleMailDeleteIncomingEmailAndRestoreToInboxTest {

    private WebDriver driver;

////    Browser setup for local run
//    @BeforeMethod(alwaysRun = true)
//    public void browserSetup() {
//        driver = new ChromeDriver();
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//    }

    //    Browser setup for Selenium Grid
    @BeforeMethod(alwaysRun = true)
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\BrowserDrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setCapability("platformName", Platform.WINDOWS);

        try{
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().deleteAllCookies();
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

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(deleteEmailTargetMessage), "Email was not moved to trash bin!");

        GoogleMailTrashBinPage trashBinPage = homePage.openTrashBinPage();
        emailDetailsPage = trashBinPage.openDeletedEmail(emailSender);
        homePage = emailDetailsPage.moveEmailFromTrashBinToInbox();

        Assert.assertTrue(emailDetailsPage.getActionConfirmationPopup()
                .getText().contains(restoreEmailtargetMessage), "Email was not moved to inbox!");

        homePage.logoutGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
