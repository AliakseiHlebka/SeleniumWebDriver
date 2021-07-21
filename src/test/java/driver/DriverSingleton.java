package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSingleton {

    private static WebDriver driver;
    private static final String FIREFOX = "firefox";
    private static final String BROWSER = "browser";

    private DriverSingleton() {}

    public static WebDriver getDriver() {
        if (null == driver) {
            if (FIREFOX.equals(System.getProperty(BROWSER))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
