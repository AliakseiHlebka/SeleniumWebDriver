package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.*;

public class GoogleMailSaveNewMailAsDraftAndDeleteTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test(description = "Create new mail, save it as draft, and then delete it from Drafts folder")
    public void googleMailSaveMailAsDraftTest() {

        final String GOOGLE_MAIL_URL = "https://mail.google.com/mail/u/0/#inbox";
        String googleMailDraftsFolderUrl = "https://mail.google.com/mail/u/0/#drafts";
        String emailAddressee = "al.hlebka@gmail.com";
        String emailSubject = "Message from Webdriver";
        String emailBody = "Hello Aliaksei,\nthis message is generated by Webdriver.\nBest regards,\nAliaksei.";

        new GoogleLoginPage(driver).loginToGoogleMail();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches(GOOGLE_MAIL_URL));

        Assert.assertSame(driver.getCurrentUrl(), GOOGLE_MAIL_URL, "Login failed");

        new GoogleMailHomePage(driver).createNewEmail();

        new GoogleMailCreateNewEmail(driver)
                .enterEmailAddressee(emailAddressee)
                .enterEmailSubject(emailSubject)
                .enterEmailBody(emailBody)
                .closeNewEmail();

        new GoogleMailHomePage(driver).openDraftsPage();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches(googleMailDraftsFolderUrl));

        new GoogleMailDraftsPage(driver).openDraftEmail(emailSubject);

        Assert.assertSame(new GoogleMailEmailDetailsPage(driver).emailAddresseeTextField.
                getText(), emailAddressee, "Email addressee is different from the expected one!");
        Assert.assertSame(new GoogleMailEmailDetailsPage(driver).emailBodyTextField
                .getText(), emailBody, "Email body is different from the expected one!");

        new GoogleMailDraftsPage(driver).deleteDratEmail();

        Assert.assertTrue(new GoogleMailDraftsPage(driver).noDraftEmailsMessage
                        .getText().contains("Нет сохраненных черновиков."), "Drafts folder is not empty!");

        new GoogleMailHomePage(driver).quitGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
