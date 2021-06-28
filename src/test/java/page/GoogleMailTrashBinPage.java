package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMailTrashBinPage extends AbstractGoogleMailPage {

    public GoogleMailTrashBinPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//tr[@class='zA yO']")
     private WebElement deletedEmail;

    public GoogleMailEmailDetailsPage openDeletedEmail() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(deletedEmail));
        deletedEmail.click();
        return new GoogleMailEmailDetailsPage(driver);
    }
}
