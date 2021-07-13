package page;

import model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginPage extends AbstractGoogleMailPage {

    private static final String GOOGLE_MAIL_URL = "https://mail.google.com";
    private WebDriverWait wait = new WebDriverWait(driver, 5);

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

    public GoogleMailHomePage loginToGoogleMail(User user) {
        driver.get(GOOGLE_MAIL_URL);
        loginOrEmailTextfield.sendKeys(user.getLogin());
        confirmEmailOrPhoneButton.click();
        log.info("Login entered");
        wait.until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(user.getPassword());
        confirmPasswordButton.click();
        log.info("Password entered");
        wait.until(ExpectedConditions.urlMatches(GoogleMailHomePage.HOMEPAGE_URL));
        log.info("Signed in successfully");
        return new GoogleMailHomePage(driver);
    }
}
