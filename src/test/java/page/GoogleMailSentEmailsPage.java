package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GoogleMailSentEmailsPage extends AbstractGoogleMailPage {

    public static final String SENT_EMAILS_URL = "https://mail.google.com/mail/u/0/#sent";

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> listOfSentEmails;

    @FindBy(xpath = "//h2[@class='hP']")
    private WebElement sentEmailSubject;

    @FindBy(xpath = "//div[@class='a3s aiL ']")
    private WebElement sentEmailBody;

    public WebElement getSentEmailSubject() {
        return sentEmailSubject;
    }

    public WebElement getSentEmailBody() {
        return sentEmailBody;
    }

    public GoogleMailSentEmailsPage(WebDriver driver) {
        super(driver);
    }

    public void openSentEmail(String text) {
        for (WebElement email : listOfSentEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
    }
}
