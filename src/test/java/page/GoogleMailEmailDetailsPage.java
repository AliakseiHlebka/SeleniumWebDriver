package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMailEmailDetailsPage extends AbstractGoogleMailPage {

    public GoogleMailEmailDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=':4']/div[2]/div[1]/div/div[2]/div[3]")
    private WebElement deleteEmailButton;

    @FindBy(xpath = "//*[@id=':au']/div[1]")
    private WebElement moveToFolderButton;

    @FindBy(xpath = "//div[@id=':cw']")
    private WebElement moveToInboxButton;

    public GoogleMailHomePage deleteEmail() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteEmailButton));
        deleteEmailButton.click();
        return new GoogleMailHomePage(driver);
    }

    public GoogleMailHomePage moveEmailFromTrashBinToInbox() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(moveToFolderButton));
        moveToFolderButton.click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(moveToInboxButton));
        moveToInboxButton.click();
        return new GoogleMailHomePage(driver);
    }
}
