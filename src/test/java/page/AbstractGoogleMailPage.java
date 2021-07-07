package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractGoogleMailPage {

    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;

    protected AbstractGoogleMailPage(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) this.driver;
        PageFactory.initElements(driver, this);
    }
}
