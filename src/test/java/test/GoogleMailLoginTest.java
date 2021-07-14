package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleLoginPage;
import page.GoogleMailHomePage;
import service.UserCreator;
import util.AlertsGenerator;

public class GoogleMailLoginTest extends CommonConditions {

    @Test(description = "Smoke")
    public void googleMailLoginTest() {
        User testUser = UserCreator.withValidCredentials();
        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);
        Assert.assertEquals(homePage.getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");
        new AlertsGenerator().generateTestPassAlert();
    }
}
