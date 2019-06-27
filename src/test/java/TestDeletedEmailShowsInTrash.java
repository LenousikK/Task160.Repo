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
public class TestDeletedEmailShowsInTrash extends BeforeAfter {
    private static final String LOGIN_USERNAME = "seleniumtests10";
    private static final String LOGIN_PASSWORD = "060788avavav";
    private static final String EMAIL_SUBJECT = "TestSubject";
    private static final String EMAIL_MESSAGE_BODY = "TestMessageBody";

    @ParameterizedTest
    @Description("Verify that deleted email is listed in Trash")
    @Feature("Gmail: When Email is deleted it shows in Trash")
    @Story("As a User I can delete Email and see it in Trash")
    @Link("Gmail-5")
    @CsvFileSource(resources = "/envData.csv")
    public void deletedEmailShowsInTrashFolder(String browserName, String runMode) throws MalformedURLException {
        driver = getDriver(browserName, runMode);
        wait = new WebDriverWait(driver, 30);
        LoginPage loginPage = new LoginPage(driver, wait);
        GmailPage gmailPage = loginPage.login(LOGIN_USERNAME, LOGIN_PASSWORD);
        gmailPage.sendEmail(LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        gmailPage.deleteEmailFromInbox(LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        assertTrue(gmailPage.checkTrashFolder(LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY));
    }

    @AfterEach
    public void finishWork() {
        GmailPage gmailPage = new GmailPage(driver, wait);
        gmailPage.deleteEmailFromTrashForever(LOGIN_USERNAME, LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        gmailPage.logout();
        closeBrowser();
    }
}