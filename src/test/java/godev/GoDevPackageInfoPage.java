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
    String XP_DETAILS_HEADER_LABEL = ".//div[@class='DetailsHeader-infoLabel']//span[contains(text(),'%s')]/following-sibling::*[1]";
    private final By BY_DETAILS_NAV_TAB = By.className("DetailsNav-tab");
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

    public HtmlElement getDetailsHeaderPublishedDateElement() {
        return new HtmlElement(webDriver, By.xpath(String.format(XP_DETAILS_HEADER_LABEL, "Published:")));
    }

    public HtmlElement getDetailsHeaderModuleNameElement() {
        return new HtmlElement(webDriver, By.xpath(String.format(XP_DETAILS_HEADER_LABEL, "Module:")));
    }

    public HtmlElement getPackageVersionElement() {
        return new HtmlElement(webDriver, BY_PACKAGE_VERSION);
    }

    public String getDetailsHeaderPublishedDate() {
        logger.info("Getting package published date");
        return getDetailsHeaderPublishedDateElement().getText();
    }

    public String getDetailsHeaderModuleName() {
        logger.info("Getting package module name");
        return getDetailsHeaderModuleNameElement().getText();
    }

    public String getPackageVersion() {
        logger.info("Getting package version");
        return getPackageVersionElement().getText();
    }

    public List<String> getDetailsNavTabNames() {
        logger.info("Getting navigation tab names");
        return webDriver.findElements(BY_DETAILS_NAV_TAB)
                .stream()
                .map(tab -> tab.getText().trim())
                .collect(Collectors.toList());
    }
}
