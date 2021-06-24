package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleLoginPage extends AbstractGoogleMailPage {

    private static final String HOMEPAGE_URL = "https://mail.google.com";
    private static final String LOGIN = "tyrmandyr1@gmail.com";
    private static final String PASSWORD = "@Tyrmandyr1!";

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

    public GoogleMailHomePage loginToGoogleMail() {
        driver.get(HOMEPAGE_URL);
        loginOrEmailTextfield.sendKeys(LOGIN);
        confirmEmailOrPhoneButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(passwordTextField));
        passwordTextField.sendKeys(PASSWORD);
        confirmPasswordButton.click();
        return new GoogleMailHomePage(driver);
    }
}
