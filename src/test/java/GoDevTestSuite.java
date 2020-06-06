import godev.GoDevLandingPage;
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

    private GoDevLandingPage navigateToGoDevLandingPage() {
        webDriver.navigate().to("https://go.dev");

        GoDevLandingPage goDevPage = new GoDevLandingPage(webDriver);
        Assert.assertTrue(goDevPage.isOpened(), "go.dev landing page should be opened");

        return goDevPage;
    }
}
