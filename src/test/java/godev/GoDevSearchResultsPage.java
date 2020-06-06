package godev;

import godev.core.HtmlElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class GoDevSearchResultsPage extends GoDevBasePage {
    private static final Logger logger = LoggerFactory.getLogger(GoDevSearchResultsPage.class);
    private final By BY_SEARCH_RESULTS_HEADER = By.cssSelector("h1.SearchResults-header");

    public GoDevSearchResultsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isOpened() {
        return getSearchResultsPageHeaderText().isDisplayed();
    }

    @Override
    public void waitUntilOpened() {
        getSearchResultsPageHeaderText().isDisplayed(5);
    }

    public HtmlElement getSearchResultsPageHeaderText() {
        return new HtmlElement(getWebDriver(), BY_SEARCH_RESULTS_HEADER);
    }
}
