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
import page.GoogleMailHomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class SimpleJavaScriptExecutorTest {

    private WebDriver driver;
    JavascriptExecutor javascriptExecutor;

//    //    Browser setup for Selenium Grid
//    @BeforeMethod(alwaysRun = true)
//    public void browserSetup() {
//        driver = new ChromeDriver();
//        javascriptExecutor = (JavascriptExecutor) driver;
//        driver.manage().deleteAllCookies();
//        driver.manage().window().maximize();
//    }

//        Browser setup for Selenium Grid
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
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().deleteAllCookies();
    }

    @Test (description = "Smoke")
    public void testJavaScript() {
        String login = "tyrmandyr1@gmail.com";
        String password = "@Tyrmandyr1!";

        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(login, password);
        Assert.assertEquals(homePage.getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");
        homePage.generateTestPassAlert();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}
