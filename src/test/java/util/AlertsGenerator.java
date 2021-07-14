package util;

import driver.DriverSingleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsGenerator {

    private WebDriver driver = DriverSingleton.getDriver();
    private JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
    private Logger log = LogManager.getRootLogger();

    public void generateTestPassAlert() {
        javascriptExecutor.executeScript("alert('Test Passed!');");
        new WebDriverWait(driver, 5).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        log.info("Test passed\n**************");
    }
}
