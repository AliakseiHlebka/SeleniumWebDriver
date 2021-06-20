package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMailCreateNewEmail {

    private WebDriver driver;

    public GoogleMailCreateNewEmail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = ":9e")
    private WebElement emailAddresseeTextfield;

    @FindBy(id = ":8w")
    private WebElement emailSubjectTextfield;

    @FindBy(id = ":a1")
    private WebElement emailBodyTextfield;

    @FindBy(xpath = "//div[text()='Отправить']")
    private WebElement sendEmailButton;

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
}
