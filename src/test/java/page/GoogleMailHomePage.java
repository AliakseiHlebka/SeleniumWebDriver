package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleMailHomePage {

    private WebDriver driver;

    public GoogleMailHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[1]/div/div")
    private WebElement createNewEmailButton;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[1]/div[1]/div/div/div/div[2]/div/div/div[1]/div[1]/div/div[4]/div/div/div[2]/span/a")
    public WebElement sentEmailsFolderLink;

    public GoogleMailCreateNewEmail createNewEmail() {
        createNewEmailButton.click();
        return new GoogleMailCreateNewEmail(driver);
    }

    public GoogleMailSentEmailsPage openSentEmailsPage() {
        sentEmailsFolderLink.click();
        return new GoogleMailSentEmailsPage(driver);
    }
}
