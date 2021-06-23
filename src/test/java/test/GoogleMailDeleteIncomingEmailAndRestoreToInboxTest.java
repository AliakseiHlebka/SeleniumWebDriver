package test;

import org.openqa.selenium.By;
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

import java.util.concurrent.TimeUnit;

public class GoogleMailDeleteIncomingEmailAndRestoreToInboxTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(description = "Delete incoming email and restore it back to Inbox")
    public void googleMailDeleteIncomingEmailAndRestoreToInboxTest() {

        String googleMailUrl = "https://mail.google.com/mail/u/0/#inbox";
        String targetMessage = "Цепочка перемещена во входящие.";

        new GoogleLoginPage(driver)
                .openPage()
                .enterEmailOrPhone("tyrmandyr1@gmail.com")
                .confirmEmailOrPhone()
                .enterPassword("@Tyrmandyr1!")
                .confirmPassword();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches(googleMailUrl));

        new GoogleMailHomePage(driver).openInboxEmail("The Google team");
        new GoogleMailEmailDetailsPage(driver).deleteEmail();
        new GoogleMailHomePage(driver).openTrashBinPage();
        new GoogleMailTrashBinPage(driver).openDeletedEmail();
        new GoogleMailEmailDetailsPage(driver).moveEmailFromTrashBinToInbox();

        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[4]/div[1]/div/div[3]/div/div/div[2]/span/span[1]"))
                .getText().contains(targetMessage), "Action was not performed!");

        new GoogleMailHomePage(driver).quitGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
