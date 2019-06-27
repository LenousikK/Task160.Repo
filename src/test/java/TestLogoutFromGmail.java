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
public class TestLogoutFromGmail extends BeforeAfter {
    @ParameterizedTest
    @Description("Verify ability to log out")
    @Feature("Gmail: Logged User is able to log out")
    @Story("As a User I can log out")
    @Link("Gmail-2")
    @CsvFileSource(resources = "/testData.csv")
    public void logoutSuccessful(String browserName, String runMode, String loginUsername, String loginPassword) throws MalformedURLException {
        driver = getDriver(browserName, runMode);
        wait = new WebDriverWait(driver, 30);
        LoginPage loginPage = new LoginPage(driver, wait);
        GmailPage gmailPage = loginPage.login(loginUsername, loginPassword);
        loginPage = gmailPage.logout();
        assertTrue(loginPage.titleOfLoginPageIsCorrect());
    }

    @AfterEach
    public void finishWork() {
        closeBrowser();
    }
}