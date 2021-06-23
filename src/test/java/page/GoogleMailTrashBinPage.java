package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleMailTrashBinPage extends AbstractGoogleMailPage {

    public GoogleMailTrashBinPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=':9u']/span/span")
     private WebElement deletedEmail;

    public GoogleMailEmailDetailsPage openDeletedEmail() {
        deletedEmail.click();
        return new GoogleMailEmailDetailsPage(driver);
    }
}
