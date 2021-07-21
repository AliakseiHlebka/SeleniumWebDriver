package util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import driver.DriverSingleton;

public class TestListener implements ITestListener {

    private Logger log = LogManager.getRootLogger();

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }

    private void saveScreenshot() {
        String screenshotPathName = ".//target/screenshots/";
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        log.error("Error screenshot taken");
        try {
            FileUtils.copyFile(screenCapture, new File(
                    screenshotPathName
                    + getCurrentTime() + ".png"));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTime() {
        String screenshotDateTimeFormat = "uuuu-MM-dd_HH-mm-ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(screenshotDateTimeFormat);
        return ZonedDateTime.now().format(formatter);
    }
}
