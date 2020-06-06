package godev.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class WebDriverManager {

    public static WebDriver setupWebDriver() {
        String browser = System.getProperty("browser");
        if (browser == null) browser = "";

        switch (browser) {
            case "edge":
                edgedriver().setup();
                return new EdgeDriver();
            case "firefox":
                firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                return new ChromeDriver(options);
        }
    }
}