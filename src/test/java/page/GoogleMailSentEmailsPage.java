package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleMailSentEmailsPage extends AbstractGoogleMailPage {

    public static final String SENT_EMAILS_URL = "https://mail.google.com/mail/u/0/#sent";

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> listOfSentEmails;

    public GoogleMailSentEmailsPage(WebDriver driver) {
        super(driver);
    }

    public GoogleMailCreateNewEmailPage openSentEmail(String text) {
        for (WebElement email : listOfSentEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
        return new GoogleMailCreateNewEmailPage(driver);
    }
}
