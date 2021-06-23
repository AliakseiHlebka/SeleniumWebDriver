package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMailCreateNewEmail extends AbstractGoogleMailPage {

    @FindBy(id = ":9e")
    private WebElement emailAddresseeTextfield;

    @FindBy(id = ":8w")
    private WebElement emailSubjectTextfield;

    @FindBy(id = ":a1")
    private WebElement emailBodyTextfield;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//*[@id=':5x']")
    private WebElement closeNewEmailButton;

    public GoogleMailCreateNewEmail(WebDriver driver) {
        super(driver);
    }

    public GoogleMailCreateNewEmail enterEmailAddressee(String email) {
        emailAddresseeTextfield.sendKeys(email);
        return this;
    }

    public GoogleMailCreateNewEmail enterEmailSubject(String subject) {
        emailSubjectTextfield.sendKeys(subject);
        return this;
    }

    public GoogleMailCreateNewEmail enterEmailBody(String text) {
        emailBodyTextfield.sendKeys(text);
        return this;
    }

    public GoogleMailHomePage sendEmail() {
        sendEmailButton.click();
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage closeNewEmail() {
        closeNewEmailButton.click();
        return new GoogleMailHomePage(driver);
    }
}
