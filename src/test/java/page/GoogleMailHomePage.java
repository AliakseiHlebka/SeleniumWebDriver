package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleMailHomePage extends AbstractGoogleMailPage {

    static final String HOMEPAGE_URL = "https://mail.google.com/mail/u/0/#inbox";
    WebDriverWait wait = new WebDriverWait(driver, 10);
    GoogleMailSentEmailsPage sentEmailsPage = new GoogleMailSentEmailsPage(driver);
    GoogleMailDraftsPage draftsPage = new GoogleMailDraftsPage(driver);

    @FindBy(xpath = "//div[text()='Написать']")
    private WebElement createNewEmailButton;

    @FindBy(xpath = "//div[@data-tooltip='Отправленные']")
    private WebElement sentEmailsFolderLink;

    @FindBy(xpath = "//a[contains(@aria-label, 'Черновики')]")
    private WebElement draftsFolderLink;

    @FindBy(xpath = "//a[contains(@aria-label, 'Аккаунт Google')]")
    private WebElement googleAccountInfoButton;

    @FindBy(xpath = "//a[text()='Выйти']")
    private WebElement quitGoogleAccountButton;
    
    @FindBy(xpath = "//span[@class='bA4']")
    private List<WebElement> listOfInboxEmails;

    @FindBy(xpath = "//span[@class='CJ'] [text()='Ещё']")
    private WebElement expandMenuOptionsButton;

    @FindBy(xpath = "//div[@data-tooltip='Корзина']")
    private WebElement trashBinFolderLink;

    public GoogleMailHomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomepageUrl() {
        return HOMEPAGE_URL;
    }

    public GoogleMailCreateNewEmailPage goToCreateNewEmail() {
        createNewEmailButton.click();
        log.info("Create new email page opened");
        return new GoogleMailCreateNewEmailPage(driver);
    }

    public GoogleMailSentEmailsPage openSentEmailsPage() {
        sentEmailsFolderLink.click();
        wait.until(ExpectedConditions.urlMatches(sentEmailsPage.SENT_EMAILS_URL));
        log.info("Sent emails page opened");
        return new GoogleMailSentEmailsPage(driver);
    }

    public GoogleMailDraftsPage openDraftsPage() {
        draftsFolderLink.click();
        wait.until(ExpectedConditions.urlMatches(draftsPage.DRAFT_EMAILS_URL));
        log.info("Drafts page opened");
        return new GoogleMailDraftsPage(driver);
    }

    public void logoutGoogleAccount() {
        googleAccountInfoButton.click();
        quitGoogleAccountButton.click();
        log.info("Logout success");
    }

    public GoogleMailEmailDetailsPage openInboxEmail(String text) {
        for (WebElement email : listOfInboxEmails) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
        log.info("Inbox email opened");
        return new GoogleMailEmailDetailsPage(driver);
    }

    public GoogleMailTrashBinPage openTrashBinPage() {
        wait.until(ExpectedConditions.elementToBeClickable(expandMenuOptionsButton));
        expandMenuOptionsButton.click();
        trashBinFolderLink.click();
        log.info("Trash bin page opened");
        return new GoogleMailTrashBinPage(driver);
    }

    public String getCurrentPageUrl() {
        String currentUrl = (String) javascriptExecutor.executeScript("return document.location.href;");
        return currentUrl;
    }
}
