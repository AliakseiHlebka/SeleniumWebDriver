package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import driver.DriverSingleton;

public class CurrentUrlReader {

    private WebDriver driver = DriverSingleton.getDriver();
    private JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    public String getCurrentPageUrl() {
        return (String) javascriptExecutor.executeScript("return document.location.href;");
    }
}
