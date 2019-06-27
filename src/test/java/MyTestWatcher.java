import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

public class MyTestWatcher implements TestWatcher {
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        WebDriver driver = ((BeforeAfter) extensionContext.getTestInstance().get()).driver;
        makeScreenshotOnFailure(driver);
    }

    @Attachment(value = "Screenshot On Failure", type = "image/png")
    public byte[] makeScreenshotOnFailure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}