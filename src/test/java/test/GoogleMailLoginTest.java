package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import model.User;
import page.GoogleLoginPage;
import page.GoogleMailHomePage;
import service.ValidUserCreator;
import util.AlertsGenerator;
import util.CurrentUrlReader;

public class GoogleMailLoginTest extends CommonConditions {

    @Test(description = "Smoke")
    public void googleMailLoginTest() {
        User testUser = new ValidUserCreator().createUser();
        GoogleMailHomePage homePage = new GoogleLoginPage(driver).loginToGoogleMail(testUser);
        Assert.assertEquals(new CurrentUrlReader().getCurrentPageUrl(), homePage.getHomepageUrl(), "Login failed");
        new AlertsGenerator().generateTestPassAlert();
    }
}
