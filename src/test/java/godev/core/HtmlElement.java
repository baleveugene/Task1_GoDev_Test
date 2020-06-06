package godev.core;

import godev.managers.WaitManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HtmlElement implements WebElement {
    private final WebDriver webDriver;
    private final By locator;
    private final WaitManager waitManager;

    public HtmlElement(WebDriver webDriver, By locator) {
        this.webDriver = webDriver;
        this.locator = locator;
        this.waitManager = new WaitManager(webDriver);
    }

    //region find element
    @Override
    public HtmlElement findElement(By by) {
        return new HtmlElement(getWebDriver(), new ByChained(getLocator(), by));
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWebElement().findElements(by);
    }
    //endregion

    //region actions
    @Override
    public void click() {
        waitManager.getFluentWait(10).until((driver) -> {
            new Actions(webDriver)
                    .moveToElement(getWebElement())
                    .click(getWebElement())
                    .build()
                    .perform();
            return true;
        });
    }

    @Override
    public void submit() {
        waitManager.getWait().until(webDriver -> {
            getWebElement().submit();
            return true;
        });
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        waitManager.getWait().until(webDriver -> {
            getWebElement().sendKeys(keysToSend);
            return true;
        });
    }

    @Override
    public void clear() {
        waitManager.getWait().until(webDriver -> {
            getWebElement().clear();
            return true;
        });
    }
    //endregion

    //region checks
    @Override
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    @Override
    public boolean isDisplayed() {
        return isDisplayed(0);
    }

    public boolean isDisplayed(long timeOutInSeconds) {
        try {
            waitManager.getWait(timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
    //endregion

    //region getters
    public WebDriver getWebDriver() {
        return webDriver;
    }

    public WebElement getWebElement() {
        return getWebDriver().findElement(locator);
    }

    public By getLocator() {
        return locator;
    }

    @Override
    public String getTagName() {
        return getWebElement().getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return getWebElement().getAttribute(name);
    }

    @Override
    public String getText() {
        return getWebElement().getText();
    }

    @Override
    public Point getLocation() {
        return getWebElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWebElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWebElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWebElement().getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return getWebElement().getScreenshotAs(target);
    }
    //endregion
}
