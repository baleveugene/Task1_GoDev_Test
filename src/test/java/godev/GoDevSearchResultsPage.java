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
    private final By BY_SEARCH_RESULT_LINK = By.cssSelector("h2.SearchSnippet-header a");
    private final By BY_PAGINATION_NEXT_BUTTON = By.cssSelector("a.Pagination-next");

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

    public HtmlElement getPaginationNextButton() {
        return new HtmlElement(getWebDriver(), BY_PAGINATION_NEXT_BUTTON);
    }

    public List<String> getPageSearchResults() {
        return getWebDriver().findElements(BY_SEARCH_RESULT_LINK)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public HtmlElement getPackageLink(String packageName) {
        return new HtmlElement(getWebDriver(), By.linkText(packageName));
    }

    public GoDevSearchResultsPage navigateToNextPageWithResults() {
        getPaginationNextButton().click();
        GoDevSearchResultsPage nextPage = new GoDevSearchResultsPage(webDriver);
        nextPage.waitUntilOpened();
        return nextPage;
    }

    public GoDevSearchResultsPage navigateToPageWithNeededPackage(String packageName) {
        List<String> currentPageSearchResults = getPageSearchResults();
        int currentPageIndex = 1;
        while (!currentPageSearchResults.contains(packageName) && getPaginationNextButton().isDisplayed()) {
            logger.info(String.format("Package <%s> not found on page <%s>. Navigate to next page", packageName, currentPageIndex));
            logger.debug(String.format("Current page search results: <%s>", currentPageSearchResults));
            navigateToNextPageWithResults();
            currentPageIndex++;
            currentPageSearchResults = getPageSearchResults();

        }
        logger.info(String.format("Package <%s> was found on page <%s>", packageName, currentPageIndex));
        return new GoDevSearchResultsPage(webDriver);
    }

    public GoDevPackageInfoPage navigateToPackageInfoPage(String packageName) {
        GoDevSearchResultsPage page = navigateToPageWithNeededPackage(packageName);
        HtmlElement packageLink = page.getPackageLink(packageName);

        logger.info(String.format("Navigate to page with info about package <%s>", packageName));
        packageLink.click();
        GoDevPackageInfoPage packageInfoPage = new GoDevPackageInfoPage(webDriver);
        packageInfoPage.waitUntilOpened();

        return packageInfoPage;
    }
}