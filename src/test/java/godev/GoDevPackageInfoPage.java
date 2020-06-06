package godev;

import godev.core.HtmlElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GoDevPackageInfoPage extends GoDevBasePage {
    private static final Logger logger = LoggerFactory.getLogger(GoDevPackageInfoPage.class);
    private final By BY_PACKAGE_VERSION = By.className("DetailsHeader-version");

    public GoDevPackageInfoPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOpened() {
        return getPackageVersionElement().isDisplayed();
    }

    @Override
    public void waitUntilOpened() {
        logger.info("Waiting until page loaded");
        getPackageVersionElement().isDisplayed(5);
    }

    public HtmlElement getPackageVersionElement() {
        return new HtmlElement(webDriver, BY_PACKAGE_VERSION);
    }
}
