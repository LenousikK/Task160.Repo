import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginPage extends PageBasis {
    private static final String GMAIL_URL = "https://gmail.com";
    private static final String LOGIN_GMAIL_TITLE = "Gmail";
    private static final String INPUT_LOGIN_USERNAME = "identifierId";
    private static final String INPUT_LOGIN_PASSWORD = "input[name = 'password']";

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(GMAIL_URL);
    }

    @FindBy(id = INPUT_LOGIN_USERNAME)
    private WebElement inputLoginUsername;
    @FindBy(id = "identifierNext")
    private WebElement buttonNextAfterUsername;
    @FindBy(css = INPUT_LOGIN_PASSWORD)
    private WebElement inputLoginPassword;
    @FindBy(id = "passwordNext")
    private WebElement buttonNextAfterPassword;

    private void enterLoginUsername(String loginUsername) {
        inputLoginUsername.sendKeys(loginUsername);
    }

    private void enterLoginPassword(String loginPassword) {
        inputLoginPassword.sendKeys(loginPassword);
    }

    public GmailPage login(String loginUsername, String loginPassword) {
        open();
        wait.until(titleIs(LOGIN_GMAIL_TITLE));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(INPUT_LOGIN_USERNAME)));
        enterLoginUsername(loginUsername);
        buttonNextAfterUsername.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(INPUT_LOGIN_PASSWORD)));
        enterLoginPassword(loginPassword);
        buttonNextAfterPassword.click();
        GmailPage gmailPage = new GmailPage(driver, wait);
        gmailPage.waitUntilLinkToLoggedUserPresent();
        return gmailPage;
    }

    public boolean titleOfLoginPageIsCorrect() {
        return driver.getTitle().equals(LOGIN_GMAIL_TITLE);
    }
}