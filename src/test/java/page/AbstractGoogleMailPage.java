package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractGoogleMailPage {

    protected WebDriver driver;
    protected JavascriptExecutor javascriptExecutor;
    protected Logger log;

    protected AbstractGoogleMailPage(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) this.driver;
        this.log = LogManager.getRootLogger();
        PageFactory.initElements(driver, this);
    }
}
