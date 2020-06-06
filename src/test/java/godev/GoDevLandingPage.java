package godev;

import godev.core.HtmlElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoDevLandingPage extends GoDevBasePage {
    private static final Logger logger = LoggerFactory.getLogger(GoDevLandingPage.class);
    private final By BY_GET_STARTED_BUTTON = By.xpath(".//div[@class='Hero-actions']/a[text()='Get Started']");

    public GoDevLandingPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOpened() {
        return webDriver.getTitle().equals("go.dev") && getGetStartedButton().isDisplayed();
    }

    @Override
    public void waitUntilOpened () {
        getGetStartedButton().isDisplayed(5);
    }

    public HtmlElement getGetStartedButton() {
        return new HtmlElement(webDriver, BY_GET_STARTED_BUTTON);
    }
}
