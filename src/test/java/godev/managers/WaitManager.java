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
}