package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleMailHomePage extends AbstractGoogleMailPage {

    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private WebElement createNewEmailButton;

    @FindBy(xpath = "//*[@id=':4b']/div")
    private WebElement sentEmailsFolderLink;

    @FindBy(xpath = "//*[@id=':4d']/div")
    private WebElement draftsFolderLink;

    @FindBy(xpath = "//*[@id='gb']/div[2]/div[3]/div[1]/div[2]/div/a/img")
    private WebElement googleAccountInfoButton;

    @FindBy(xpath = "//a[@id='gb_71']")
    private WebElement quitGoogleAccountButton;
    
    @FindBy(xpath = "//span[@class='bA4']")
    private List<WebElement> listOfInboxEmails;

    @FindBy(xpath = "//span[@class='J-Ke n4 ah9']")
    private WebElement expandMenuOptionsButton;

    @FindBy(xpath = "//div[@class='TN bzz aHS-bnx']")
    private WebElement trashBinFolderLink;

    public GoogleMailHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleMailCreateNewEmail createNewEmail() {
        createNewEmailButton.click();
        return new GoogleMailCreateNewEmail(driver);
    }

    public GoogleMailSentEmailsPage openSentEmailsPage() {
        sentEmailsFolderLink.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.urlMatches(new GoogleMailSentEmailsPage(driver).SENT_EMAILS_URL));
        return new GoogleMailSentEmailsPage(driver);
    }

    public GoogleMailDraftsPage openDraftsPage() {
        draftsFolderLink.click();
        return new GoogleMailDraftsPage(driver);
    }

    public void quitGoogleAccount() {
        googleAccountInfoButton.click();
        quitGoogleAccountButton.click();
    }

    public void openInboxEmail(String text) {
        for (WebElement email : listOfInboxEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
    }

    public GoogleMailTrashBinPage openTrashBinPage() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(expandMenuOptionsButton));
        expandMenuOptionsButton.click();
        trashBinFolderLink.click();
        return new GoogleMailTrashBinPage(driver);
    }
}
