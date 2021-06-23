package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleMailHomePage extends AbstractGoogleMailPage {

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div/div")
    private WebElement createNewEmailButton;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div/div[1]/div[1]/div/div[4]/div/div/div[2]/span/a")
    private WebElement sentEmailsFolderLink;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div/div[1]/div[1]/div/div[5]/div/div/div[2]/span/a")
    private WebElement draftsFolderLink;

    @FindBy(xpath = "//*[@id='gb']/div[2]/div[3]/div[1]/div[2]/div/a/img")
    private WebElement googleAccountInfoButton;

    @FindBy(xpath = "//a[@id='gb_71']")
    private WebElement quitGoogleAccountButton;
    
    @FindBy(xpath = "//span[@class='bA4']")
    private List<WebElement> listOfInboxEmails;

    @FindBy(xpath = "//*[@id=':40']")
    private WebElement expandMenuOptionsButton;

    @FindBy(xpath = "//*[@id=':4g']")
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
        return new GoogleMailSentEmailsPage(driver);
    }

    public GoogleMailDraftsPage openDraftsPage() {
        draftsFolderLink.click();
        return new GoogleMailDraftsPage(driver);
    }

    public GoogleMailHomePage quitGoogleAccount() {
        googleAccountInfoButton.click();
        quitGoogleAccountButton.click();
        return null;
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
