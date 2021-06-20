package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class GoogleMailSentEmailsPage {

    private WebDriver driver;

    public GoogleMailSentEmailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='bog']")
    private List<WebElement> listOfEmails;

    public void openSentEmail(String subject) {
        for (WebElement listOfEmail : listOfEmails) {
            if (listOfEmail.getText().contains(subject)) {
                listOfEmail.click();
                break;
            }
        }
    }
}
