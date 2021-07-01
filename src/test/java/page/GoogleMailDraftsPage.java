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

    @FindBy(xpath = "//div[contains(@aria-label, 'Удалить черновик')]")
    private WebElement deleteDraftEmailButton;

    @FindBy(xpath = "//td[@class='TC']")
    private WebElement noDraftEmailsMessage;

    @FindBy(xpath = "//*[@id=':7z']/span")
    private WebElement emailAddresseeTextField;

    public GoogleMailDraftsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getNoDraftEmailsMessage() {
        return noDraftEmailsMessage;
    }

    public void openDraftEmail(String text) {
        for (WebElement email : listOfDraftEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(emailAddresseeTextField));
    }

    public void deleteDraftEmail() {
       deleteDraftEmailButton.click();
    }
}
