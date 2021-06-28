package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginPage extends AbstractGoogleMailPage {

    private static final String HOMEPAGE_URL = "https://mail.google.com";

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

    public GoogleMailHomePage loginToGoogleMail(String login, String password) {
        driver.get(HOMEPAGE_URL);
        loginOrEmailTextfield.sendKeys(login);
        confirmEmailOrPhoneButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(password);
        confirmPasswordButton.click();
        return new GoogleMailHomePage(driver);
    }
}
