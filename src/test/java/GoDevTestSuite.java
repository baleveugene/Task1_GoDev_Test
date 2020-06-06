import godev.GoDevLandingPage;
import godev.GoDevPackageInfoPage;
import godev.GoDevSearchResultsPage;
import godev.managers.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class GoDevTestSuite {
    private WebDriver webDriver;

    @BeforeTest
    private void setup() {
        webDriver = WebDriverManager.setupWebDriver();
    }

    @AfterTest
    private void tearDown() {
        if (webDriver != null) webDriver.quit();
    }

    @Test
    public void navBarOptionsPresenceCheck() {
        GoDevLandingPage goDevPage = navigateToGoDevLandingPage();

        List<String> actualNavBarOptions = goDevPage.getHeader().getNavigationBarOptionNames();
        List<String> expectedNavBarOptions = Arrays.asList(
                "Why Go", "Getting Started", "Discover Packages", "About"
        );

        Assert.assertEquals(actualNavBarOptions, expectedNavBarOptions);
    }

    @Test
    public void packageInfoUiChecks() {
        String packageName = "github.com/mikekonan/protoc-gen-setter";
        String searchCriteria = "setter";

        GoDevLandingPage goDevPage = navigateToGoDevLandingPage();

        GoDevSearchResultsPage searchResultsPage = goDevPage.getHeader().searchForAPackage(searchCriteria);
        Assert.assertTrue(searchResultsPage.isOpened(), String.format("Page with search results for '%s' should be opened", searchCriteria));

        GoDevPackageInfoPage packageInfoPage = searchResultsPage.navigateToPackageInfoPage(packageName);
        Assert.assertTrue(packageInfoPage.isOpened(), String.format("Page for package '%s' should be opened", packageName));

        List<String> actualNavTabNames = packageInfoPage.getDetailsNavTabNames();
        List<String> expectedNavTabNames = Arrays.asList(
                "Doc", "Overview", "Subdirectories", "Versions", "Imports", "Imported By", "Licenses"
        );
        Assert.assertEquals(
                actualNavTabNames, expectedNavTabNames,
                String.format("Page should contains following tabs: <%s>. But actual tabs are: <%s>", expectedNavTabNames, actualNavTabNames)
        );

        String actualPublishedDate = packageInfoPage.getDetailsHeaderPublishedDate();
        String expectedPublishedDate = "Apr 13, 2020";
        Assert.assertEquals(actualPublishedDate, expectedPublishedDate);

        String actualVersion = packageInfoPage.getPackageVersion();
        String expectedVersion = "v1.3.2";
        Assert.assertEquals(actualVersion, expectedVersion);

        String actualModule = packageInfoPage.getDetailsHeaderModuleName();
        Assert.assertEquals(actualModule, packageName);
    }

    private GoDevLandingPage navigateToGoDevLandingPage() {
        webDriver.navigate().to("https://go.dev");

        GoDevLandingPage goDevPage = new GoDevLandingPage(webDriver);
        Assert.assertTrue(goDevPage.isOpened(), "go.dev landing page should be opened");

        return goDevPage;
    }
}
