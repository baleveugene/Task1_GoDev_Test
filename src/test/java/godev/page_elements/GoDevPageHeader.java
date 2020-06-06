package godev.page_elements;

import godev.GoDevSearchResultsPage;
import godev.core.HtmlElement;
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
    private final By BY_SEARCH_FIELD = By.cssSelector("input[title='Search for a package']");
    private final By BY_SUBMIT_SEARCH_BUTTON = By.cssSelector("button[aria-label='Search for a package']");

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

    public HtmlElement getSearchField() {
        return new HtmlElement(webDriver, BY_SEARCH_FIELD);
    }

    public WebElement getSubmitSearchButton() {
        return new HtmlElement(webDriver, BY_SUBMIT_SEARCH_BUTTON);
    }

    public GoDevSearchResultsPage searchForAPackage(String searchCriteria) {
        logger.info(String.format("Search package by criteria <%s>", searchCriteria));
        getSearchField().clear();
        getSearchField().sendKeys(searchCriteria);
        getSubmitSearchButton().click();
        return new GoDevSearchResultsPage(webDriver);
    }
}