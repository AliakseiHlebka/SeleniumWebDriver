package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMailEmailDetailsPage extends AbstractGoogleMailPage {

    private WebDriverWait wait = new WebDriverWait(driver, 5);

    @FindBy(xpath = "//div[contains(@aria-label, 'Удалить черновик')]")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//div[@aria-label='Переместить в']")
    private WebElement moveToFolderButton;

    @FindBy(xpath = "//div[text()='Входящие']")
    private WebElement moveToInboxButton;

    @FindBy(xpath = "//span[@class='bAq']")
    private WebElement actionConfirmationPopup;

    @FindBy(xpath = "//*[@id=':7z']/span")
    private WebElement emailAddresseeTextField;

    @FindBy(xpath = "//div[@aria-label='Тело письма']")
    private WebElement emailBodyTextField;

    public GoogleMailEmailDetailsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getActionConfirmationPopup() {
        return actionConfirmationPopup;
    }

    public WebElement getEmailAddresseeTextField() {
        return emailAddresseeTextField;
    }

    public WebElement getEmailBodyTextField() {
        return emailBodyTextField;
    }

    public GoogleMailHomePage deleteEmail() {
        wait.until(ExpectedConditions.visibilityOf(deleteEmailButton));
        deleteEmailButton.click();
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage moveEmailFromTrashBinToInbox() {
        wait.until(ExpectedConditions.visibilityOf(moveToFolderButton));
        moveToFolderButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(moveToInboxButton));
        moveToInboxButton.click();
        return new GoogleMailHomePage(driver);
    }
}
