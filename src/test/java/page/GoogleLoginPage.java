package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginPage extends AbstractGoogleMailPage {

    private static final String GOOGLE_MAIL_URL = "https://mail.google.com";
    private static final String HOMEPAGE_URL = "https://mail.google.com/mail/u/0/#inbox";

    @FindBy(id = "identifierId")
    private WebElement loginOrEmailTextfield;

    @FindBy(id = "identifierNext")
    private WebElement confirmEmailOrPhoneButton;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextField;

    @FindBy(id = "passwordNext")
    private WebElement confirmPasswordButton;

    public GoogleLoginPage(WebDriver driver) {
        super(driver);
    }

    public String getHomepageUrl() {
        return HOMEPAGE_URL;
    }

    public void loginToGoogleMail(String login, String password) {
        driver.get(GOOGLE_MAIL_URL);
        loginOrEmailTextfield.sendKeys(login);
        confirmEmailOrPhoneButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
        confirmPasswordButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches(HOMEPAGE_URL));
    }
}
