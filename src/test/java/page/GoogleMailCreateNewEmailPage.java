package page;

import model.Email;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public GoogleMailCreateNewEmailPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEmailSubject() {
        return emailSubject;
    }

    public WebElement getEmailBody() {
        return emailBody;
    }

    public GoogleMailCreateNewEmailPage fillInEmailData(Email email) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(emailAddresseeTextField));
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

    public GoogleMailHomePage sendEmail() {
        sendEmailButton.click();
        log.info("Email sent");
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage closeNewEmail() {
        closeNewEmailButton.click();
        log.info("New email closed and saved as a draft");
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailCreateNewEmailPage copyEmailSubject() {
        new Actions(driver).click(emailSubjectTextField)
                .doubleClick()
                .keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL)
                .build().perform();
        return this;
    }

    public GoogleMailCreateNewEmailPage pasteCopiedValueToEmailBody() {
        new Actions(driver).click(emailBodyTextField).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL)
                .build().perform();
        return this;
    }
}
