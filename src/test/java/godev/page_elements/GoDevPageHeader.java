package godev.page_elements;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoDevPageHeader {
    private static final Logger logger = LoggerFactory.getLogger(GoDevPageHeader.class);
    private final WebDriver webDriver;

    public GoDevPageHeader(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}