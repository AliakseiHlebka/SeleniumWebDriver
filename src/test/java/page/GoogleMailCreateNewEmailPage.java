package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import model.Email;

public class GoogleMailCreateNewEmailPage extends AbstractGoogleMailPage {

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement emailAddresseeTextField;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement emailSubjectTextField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement emailBodyTextField;

    @FindBy(xpath = "//h2[@class='hP']")
    private WebElement emailSubject;

    @FindBy(xpath = "//div[@class='a3s aiL ']")
    private WebElement emailBody;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement sendEmailButton;

    @FindBy(xpath = "//img[@aria-label='Сохранить и закрыть']")
    private WebElement closeNewEmailButton;

    @FindBy(xpath = "//span[@class='bAq']")
    private WebElement actionConfirmationPopup;

    public GoogleMailCreateNewEmailPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEmailSubject() {
        return emailSubject;
    }

    public WebElement getEmailBody() {
        return emailBody;
    }

    public WebElement getActionConfirmationPopup() {
        return actionConfirmationPopup;
    }

    public GoogleMailCreateNewEmailPage fillInEmailData(Email email) {
        wait.until(ExpectedConditions.visibilityOf(emailAddresseeTextField));
        emailAddresseeTextField.sendKeys(email.getEmailAddressee());
        isInputDataEmpty(email.getEmailAddressee(), "Email addressee");
        emailSubjectTextField.sendKeys(email.getEmailSubject());
        isInputDataEmpty(email.getEmailSubject(), "Email subject");
        emailBodyTextField.sendKeys(email.getEmailBody());
        isInputDataEmpty(email.getEmailBody(), "Email body");
        return this;
    }

    public void isInputDataEmpty(String checkedField, String checkedFieldName) {
        if (checkedField.isEmpty()) {
            log.warn(checkedFieldName + " is empty");
        } else {
            log.info(checkedFieldName + " entered");
        }
    }

    public GoogleMailCreateNewEmailPage enterEmailAddressee(String email) {
        emailAddresseeTextField.sendKeys(email);
        return this;
    }

    public GoogleMailCreateNewEmailPage enterEmailSubject(String subject) {
        emailSubjectTextField.sendKeys(subject);
        return this;
    }

    public GoogleMailCreateNewEmailPage eneterEmailBody(String text) {
        emailBodyTextField.sendKeys(text);
        return this;
    }

    public GoogleMailHomePage sendEmail() {
        sendEmailButton.click();
        log.info("Email sent");
        wait.until(ExpectedConditions.textToBePresentInElement(actionConfirmationPopup, "Письмо отправлено."));
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage closeEmailPopupWindow() {
        closeNewEmailButton.click();
        log.info("New email closed");
        return new GoogleMailHomePage(driver);
    }
}
