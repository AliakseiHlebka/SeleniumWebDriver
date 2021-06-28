package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleMailDraftsPage extends AbstractGoogleMailPage {

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> listOfDraftEmails;

    @FindBy(xpath = "//*[@id=':7m']/span")
    private WebElement newEmailHeader;

    @FindBy(xpath = "//*[@id=':8u']")
    private WebElement deleteDraftEmailButton;

    @FindBy(xpath = "//td[@class='TC']")
    private WebElement noDraftEmailsMessage;

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
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(newEmailHeader));
    }

    public void deleteDratEmail() {
       deleteDraftEmailButton.click();
    }
}
