package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

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

    public GoogleMailCreateNewEmailPage enterEmailAddressee(String email) {
        emailAddresseeTextField.sendKeys(email);
        return this;
    }

    public GoogleMailCreateNewEmailPage enterEmailSubject(String subject) {
//        emailSubjectTextField.sendKeys(subject);
        WebElement element = emailSubjectTextField;
        javascriptExecutor.executeScript("arguments[0].value='"+ subject +"';", element);
        return this;
    }

    public GoogleMailCreateNewEmailPage enterEmailBody(String text) {
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
