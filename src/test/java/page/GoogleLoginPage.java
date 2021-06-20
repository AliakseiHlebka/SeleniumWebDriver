package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleLoginPage {

    private static final String HOMEPAGE_URL = "https://mail.google.com";
    private WebDriver driver;

    @FindBy(id = "identifierId")
    private WebElement loginOrEmailTextfield;

    @FindBy(id = "identifierNext")
    private WebElement confirmEmailOrPhoneButton;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextField;

    @FindBy(id = "passwordNext")
    private WebElement confirmPasswordButton;

    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleLoginPage openPage() {
        driver.get(HOMEPAGE_URL);
        return this;
    }

    public GoogleLoginPage enterEmailOrPhone(String login) {
        loginOrEmailTextfield.sendKeys(login);
        return this;
    }

    public GoogleLoginPage confirmEmailOrPhone() {
        confirmEmailOrPhoneButton.click();
        return this;
    }

    public GoogleLoginPage enterPassword(String password) {
        passwordTextField.sendKeys(password);
        return this;
    }

    public GoogleMailHomePage confirmPassword() {
        confirmPasswordButton.click();
        return new GoogleMailHomePage(driver);
    }
}
