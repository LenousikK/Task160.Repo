import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Execution(ExecutionMode.CONCURRENT)
public class BeforeAfter {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final String URL_TO_HUB = "http://localhost:4444/wd/hub";

    public static WebDriver getDriver(String browser, String runMode) throws MalformedURLException {
        switch (runMode) {
            case "local":
                if (browser.equals("chrome")) {
                    return new ChromeDriver();
                }
                return new FirefoxDriver();
            case "grid":
                return new RemoteWebDriver(new URL(URL_TO_HUB), getBrowserCapabilities(browser));
        }
        return null;
    }

    private static MutableCapabilities getBrowserCapabilities(String browserType) {
        switch (browserType) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability("browser", "Chrome");
                chromeOptions.setCapability("browser_version", "75.0");
                chromeOptions.setCapability("os", "Windows");
                chromeOptions.setCapability("os_version", "10");
                chromeOptions.setCapability("resolution", "1024x768");
                return chromeOptions;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("browser", "Firefox");
                firefoxOptions.setCapability("browser_version", "67.0");
                firefoxOptions.setCapability("os", "Windows");
                firefoxOptions.setCapability("os_version", "7");
                firefoxOptions.setCapability("resolution", "800x600");
                return firefoxOptions;
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
                return new ChromeOptions();
        }
    }

    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}