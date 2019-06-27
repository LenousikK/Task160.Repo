import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("Gmail")
@ExtendWith(MyTestWatcher.class)
@RunWith(JUnitPlatform.class)
public class TestLoginToGmail extends BeforeAfter {
    @ParameterizedTest
    @Description("Verify ability to log in")
    @Feature("Gmail: User is able to log in with username and password")
    @Story("As a User I can log in with username and password")
    @Link("Gmail-1")
    @CsvFileSource(resources = "/testData.csv")
    public void loginSuccessful(String browserName, String runMode, String loginUsername, String loginPassword) throws MalformedURLException {
        driver = getDriver(browserName, runMode);
        wait = new WebDriverWait(driver, 30);
        LoginPage loginPage = new LoginPage(driver, wait);
        GmailPage gmailPage = loginPage.login(loginUsername, loginPassword);
        assertTrue(gmailPage.loggedUserContainsLoginUsername(loginUsername));
    }

    @AfterEach
    public void finishWork() {
        GmailPage gmailPage = new GmailPage(driver, wait);
        gmailPage.logout();
        closeBrowser();
    }
}