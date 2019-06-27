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
public class TestSentEmailShowsInSentFolder extends BeforeAfter {
    private static final String SENDER_LOGIN_USERNAME = "seleniumtests10";
    private static final String SENDER_LOGIN_PASSWORD = "060788avavav";
    private static final String RECEPIENT_LOGIN_USERNAME = "seleniumtests30";
    private static final String EMAIL_SUBJECT = "TestSubject";
    private static final String EMAIL_MESSAGE_BODY = "TestMessageBody";

    @ParameterizedTest
    @Description("Verify that sent email appears in Sent Mail folder")
    @Feature("Gmail: When Email is sent it shows in Sent")
    @Story("As a User I can send email and see it in Sent folder")
    @Link("Gmail-4")
    @CsvFileSource(resources = "/envData.csv")
    public void sentEmailShowsInSentFolder(String browserName, String runMode) throws MalformedURLException {
        driver = getDriver(browserName, runMode);
        wait = new WebDriverWait(driver, 30);
        LoginPage loginPage = new LoginPage(driver, wait);
        GmailPage gmailPage = loginPage.login(SENDER_LOGIN_USERNAME, SENDER_LOGIN_PASSWORD);
        gmailPage.sendEmail(RECEPIENT_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        assertTrue(gmailPage.checkSentFolder(RECEPIENT_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY));
    }

    @AfterEach
    public void finishWork() {
        GmailPage gmailPage = new GmailPage(driver, wait);
        gmailPage.deleteEmailFromSent(RECEPIENT_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        gmailPage.deleteEmailFromTrashForever(SENDER_LOGIN_USERNAME, SENDER_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage = gmailPage.logout();
        loginPage.login(RECEPIENT_LOGIN_USERNAME, SENDER_LOGIN_PASSWORD);
        gmailPage.deleteEmailFromInbox(SENDER_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        gmailPage.deleteEmailFromTrashForever(RECEPIENT_LOGIN_USERNAME, SENDER_LOGIN_USERNAME, EMAIL_SUBJECT, EMAIL_MESSAGE_BODY);
        gmailPage.logout();
        closeBrowser();
    }
}