package godev.managers;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitManager {
    WebDriver webDriver;
    long defaultTimeoutInSeconds = 5;

    public WaitManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public FluentWait<WebDriver> getFluentWait() {
        return getWait(defaultTimeoutInSeconds);
    }

    public FluentWait<WebDriver> getFluentWait(long timeOutInSeconds) {
        return new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NotFoundException.class);
    }

    public WebDriverWait getWait() {
        return getWait(defaultTimeoutInSeconds);
    }

    public WebDriverWait getWait(long timeOutInSeconds) {
        return (WebDriverWait) new WebDriverWait(webDriver, timeOutInSeconds)
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NotFoundException.class);
    }
}