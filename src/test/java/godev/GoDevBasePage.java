package godev;

import godev.page_elements.GoDevPageHeader;
import org.openqa.selenium.WebDriver;

public abstract class GoDevBasePage {
    protected WebDriver webDriver;

    public GoDevBasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public boolean isOpened() {
        return webDriver.getTitle().equals("go.dev");
    }

    abstract void waitUntilOpened();

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public GoDevPageHeader getHeader() {
        return new GoDevPageHeader(webDriver);
    }
}
