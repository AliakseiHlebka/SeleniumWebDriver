package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMailDraftsPage extends AbstractGoogleMailPage {

    public GoogleMailDraftsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=':c8']/div[1]/span")
    private WebElement selectAllDraftEmailsCheckbox;

    @FindBy(xpath = "//*[@id=':4']/div[2]/div[1]/div[1]/div/div/div[2]/div/div")
    private WebElement deleteAllDraftEmailsButton;

    public GoogleMailDraftsPage selectAllDraftEmails() {
        selectAllDraftEmailsCheckbox.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteAllDraftEmailsButton));
        return new GoogleMailDraftsPage(driver);
    }

    public void deleteAllDraftEmails() {
        deleteAllDraftEmailsButton.click();
    }
}
