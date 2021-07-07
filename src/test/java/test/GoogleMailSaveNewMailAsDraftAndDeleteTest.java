package test;

import org.openqa.selenium.JavascriptExecutor;
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
import page.GoogleMailCreateNewEmailPage;
import page.GoogleMailDraftsPage;
import page.GoogleMailEmailDetailsPage;
import page.GoogleMailHomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleMailSaveNewMailAsDraftAndDeleteTest {

    private WebDriver driver;
    JavascriptExecutor javascriptExecutor;

////    Browser setup for local run
//    @BeforeMethod(alwaysRun = true)
//    public void browserSetup() {
//        driver = new ChromeDriver();
//        javascriptExecutor = (JavascriptExecutor) driver;
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

    @Test(description = "Create new mail, save it as draft, and then delete it from Drafts folder")
    public void googleMailSaveMailAsDraftTest() {

        String login = "tyrmandyr1@gmail.com";
        String password = "@Tyrmandyr1!";
        String emailAddressee = "al.hlebka@gmail.com";
        String emailSubject = "Message from Webdriver";
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(login, password);

//        Assert.assertEquals(driver.getCurrentUrl(), homePage.getHomepageUrl(), "Login failed");
        Assert.assertEquals(homePage.getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");

        GoogleMailCreateNewEmailPage createNewEmailPage = homePage.createNewEmail();
        createNewEmailPage.enterEmailAddressee(emailAddressee)
                .enterEmailSubject(emailSubject)
                .copyEmailSubject()
                .pasteCopiedValueToEmailBody();
        homePage = createNewEmailPage.closeNewEmail();
        GoogleMailDraftsPage draftsPage = homePage.openDraftsPage();
        GoogleMailEmailDetailsPage emailDetailsPage = draftsPage.openDraftEmail(emailSubject);

        Assert.assertEquals(emailDetailsPage.getEmailAddresseeTextField()
                .getText(), emailAddressee, "Email addressee is different from the expected one!");
        Assert.assertEquals(emailDetailsPage.getEmailBodyTextField()
                .getText(), emailSubject, "Email body is different from the expected one!");

        draftsPage = emailDetailsPage.deleteDraftEmailWithHotKeys();

        Assert.assertTrue(draftsPage.getNoDraftEmailsMessage().getText()
                        .contains("Нет сохраненных черновиков."), "Drafts folder is not empty!");

        homePage.logoutGoogleAccount();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
