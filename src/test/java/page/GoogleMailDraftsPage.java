package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleMailDraftsPage extends AbstractGoogleMailPage {

    public static final String DRAFT_EMAILS_URL = "https://mail.google.com/mail/u/0/#drafts";

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> listOfDraftEmails;

    @FindBy(xpath = "//td[@class='TC']")
    private WebElement noDraftEmailsMessage;

    @FindBy(xpath = "//*[@id=':7z']/span")
    private WebElement emailAddresseeTextField;

    @FindBy (xpath = "//*[@id=':68']/span")
    private WebElement draftEmailLabel;

    public GoogleMailDraftsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNoDraftEmailsMessage() {
        return noDraftEmailsMessage;
    }

    public GoogleMailEmailDetailsPage openDraftEmail(String text) {
//        try {
//            for (WebElement email : listOfDraftEmails) {
//                if (email.getText().contains(text)) {
//                    email.click();
//                    break;
//                }
//            }
//        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
//            for (WebElement email : listOfDraftEmails) {
//                if (email.getText().contains(text)) {
//                    email.click();
//                    break;
//                }
//            }
//        }
        for (WebElement email : listOfDraftEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(emailAddresseeTextField));
        log.info("Draft email opened");
        return new GoogleMailEmailDetailsPage(driver);
    }
}
