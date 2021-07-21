package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GoogleMailTrashBinPage extends AbstractGoogleMailPage {

    public static final String TRASH_BIN_URL = "https://mail.google.com/mail/u/0/#trash";

    @FindBy(xpath = "//tr[@class='zA yO']")
    private List<WebElement> deletedEmailsList;

    public GoogleMailTrashBinPage(WebDriver driver) {
        super(driver);
    }

    public GoogleMailEmailDetailsPage openDeletedEmail(String text) {
        wait.until(ExpectedConditions.urlMatches(TRASH_BIN_URL));
        for (WebElement email : deletedEmailsList) {
            if (email.getText().contains(text)) {
                email.click();
                break;
            }
        }
        log.info("Deleted email opened");
        return new GoogleMailEmailDetailsPage(driver);
    }
}
