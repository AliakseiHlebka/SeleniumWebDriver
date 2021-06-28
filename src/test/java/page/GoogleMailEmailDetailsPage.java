package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMailEmailDetailsPage extends AbstractGoogleMailPage {

    @FindBy(xpath = "//*[@id=':4']/div[2]/div[1]/div/div[2]/div[3]")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//*[@id=':7i']")
    private WebElement moveToFolderButton;

    @FindBy(xpath = "//div[@id=':9a']")
    private WebElement moveToInboxButton;

    @FindBy(xpath = "//span[@class='bAq']")
    private WebElement actionConfirmationPopup;

    @FindBy(xpath = "//*[@id=':7m']/span")
    private WebElement emailAddresseeTextField;

    @FindBy(xpath = "//*[@id=':ak']")
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
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteEmailButton));
        deleteEmailButton.click();
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage moveEmailFromTrashBinToInbox() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(moveToFolderButton));
        moveToFolderButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(moveToInboxButton));
        moveToInboxButton.click();
        return new GoogleMailHomePage(driver);
    }
}
