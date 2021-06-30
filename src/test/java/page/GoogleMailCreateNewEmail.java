package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMailCreateNewEmail extends AbstractGoogleMailPage {

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement emailAddresseeTextField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement emailSubjectTextField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement emailBodyTextField;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//img[@aria-label='Сохранить и закрыть']")
    private WebElement closeNewEmailButton;

    public GoogleMailCreateNewEmail(WebDriver driver) {
        super(driver);
    }

    public GoogleMailCreateNewEmail enterEmailAddressee(String email) {
        emailAddresseeTextField.sendKeys(email);
        return this;
    }

    public GoogleMailCreateNewEmail enterEmailSubject(String subject) {
        emailSubjectTextField.sendKeys(subject);
        return this;
    }

    public GoogleMailCreateNewEmail enterEmailBody(String text) {
        emailBodyTextField.sendKeys(text);
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
