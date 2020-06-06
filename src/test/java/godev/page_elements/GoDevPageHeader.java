package godev.page_elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GoDevPageHeader {
    private static final Logger logger = LoggerFactory.getLogger(GoDevPageHeader.class);
    private final WebDriver webDriver;
    private final By BY_NAVIGATION_BAR_ITEM = By.cssSelector("li.Header-menuItem");

    public GoDevPageHeader(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> getNavigationBarOptions() {
        return webDriver.findElements(BY_NAVIGATION_BAR_ITEM);
    }

    public List<String> getNavigationBarOptionNames() {
        logger.info("Getting navigation bar option names");
        return getNavigationBarOptions()
                .stream()
                .map(WebElement::getText)
                .collect(toList());
    }
}