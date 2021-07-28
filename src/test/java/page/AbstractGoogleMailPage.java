package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractGoogleMailPage {

    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;
    protected Logger log;
    protected static final int WAIT_TIMEOUT_SECONDS = 7;
    protected WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@aria-label, 'Аккаунт Google')]")
    private WebElement googleAccountInfoButton;

    @FindBy(xpath = "//a[text()='Выйти']")
    private WebElement quitGoogleAccountButton;

    protected AbstractGoogleMailPage(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) this.driver;
        this.log = LogManager.getRootLogger();
        this.wait = new WebDriverWait(this.driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public void logoutGoogleAccount() {
        googleAccountInfoButton.click();
        quitGoogleAccountButton.click();
        log.info("Logout success");
    }
}
