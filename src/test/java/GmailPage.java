import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GmailPage extends PageBasis {
    private static final String LINK_TO_LOGGED_USER = "a.gb_x.gb_Ea.gb_f";
    private static final String ATTRIBUTE_LOGGED_USER = "aria-label";
    private static final String MASK_PREVENTING_CLICK = "div[jsname = 'k4HEge']";
    private static final String BUTTON_TO_DELETE_SELECTED_ACCOUNT = "ul.FPFGmf li.M8HEDc.eARute.W7Aapd.bxPAYd.SmR8.znIWoc ~ li div.BHzsHc";
    private static final String EMAIL_TO_SELECT_AND_DELETE = "div.vR13fe.k6Zj8d.SmR8";
    private static final String BUTTON_TO_CONFIRM_EMAIL_DELETION = "div.XfpsVe.J9fJmf span.CwaK9 > span.RveJvd.snByac";
    private static final String INPUT_EMAIL_TO = "textarea[name = 'to']";
    private static final String TOOLTIP_ACTION_SUCCESSFUL = "div.vh > span.aT > span.bAq";
    private static final String MAIL_BOX_DOMAIN = "@gmail.com";
    private static final String HINT_NEW_EMAIL_ARRIVED = "tbody > tr.zA.zE > td.yX.xY > div.aOe > div.aRI";
    private static final String ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH = "email";
    private static final String ATTRIBUTE_NAME_INPUT_FILTER_TO_COMPARE_WITH = "value";
    private static final String ATTRIBUTE_VALUE_FILTER_BY_SENT = "in:sent ";
    private static final String ATTRIBUTE_VALUE_FILTER_BY_TRASH = "in:trash ";
    private static final By INBOX_EMAIL_RECEPIENT = By.cssSelector("span.bA4 > span");
    private static final By INBOX_EMAIL_SUBJECT = By.cssSelector("div.xS div.y6 > span > span");
    private static final By INBOX_EMAIL_MSG_BODY = By.cssSelector("td > div.xS div.y6 ~ span");
    private static final By SENT_EMAIL_RECEPIENT = By.cssSelector("td.yX.xY > div.yW > span");
    private static final By SENT_EMAIL_SUBJECT = By.cssSelector("td.xY.a4W > div.xS > div.xT > div.y6 > span.bog > span");
    private static final By SENT_EMAIL_MSG_BODY = By.cssSelector("td.xY.a4W > div.xS > div.xT > span");
    private static final By INBOX_EMAIL_RECEPIENT_SENT_TO_SELF = By.cssSelector("td.yX.xY > div.yW > span > span");
    private static final By INBOX_EMAIL_SUBJECT_SENT_TO_SELF = By.cssSelector("td.xY.a4W > div.xS > div.xT > div.y6 > span.bog > span");
    private static final By INBOX_EMAIL_MSG_BODY_SENT_TO_SELF = By.cssSelector("td.xY.a4W > div.xS > div.xT > div.y6 + span");
    private static final By TRASH_EMAIL_RECEPIENT = By.cssSelector("td.yX.xY > div.yW > span > span");
    private static final By TRASH_EMAIL_SUBJECT = By.cssSelector("td.xY.a4W > div.xS > div.xT > div.y6 > span.bog > span");
    private static final By TRASH_EMAIL_MSG_BODY = By.cssSelector("td.xY.a4W > div.xS > div.xT > span.y2");
    private static final By CHECKBOX_TO_SELECT_EMAIL = By.cssSelector("div.oZ-jc.T-Jo.J-J5-Ji");

    public GmailPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = LINK_TO_LOGGED_USER)
    private WebElement linkToLoggedUser;
    @FindBy(css = "div a.gb_0.gb_Rf.gb_Zf.gb_ye.gb_jb")
    private WebElement linkToSignOut;
    @FindBy(id = "profileIdentifier")
    private WebElement dropDownWithEmailToExpand;
    @FindBy(css = BUTTON_TO_DELETE_SELECTED_ACCOUNT)
    private WebElement buttonToDeleteSelectedAccount;
    @FindBy(css = EMAIL_TO_SELECT_AND_DELETE)
    private WebElement emailToSelectAndDelete;
    @FindBy(css = BUTTON_TO_CONFIRM_EMAIL_DELETION)
    private WebElement buttonToConfirmEmailDeletion;
    @FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
    private WebElement linkCreateNewEmail;
    @FindBy(css = INPUT_EMAIL_TO)
    private WebElement inputEmailTo;
    @FindBy(css = "input[name = 'subjectbox']")
    private WebElement inputEmailSubject;
    @FindBy(css = "div[aria-label = 'Message Body']")
    private WebElement inputEmailMessageBody;
    @FindBy(css = "div[role = 'button'][aria-label *= 'Send']")
    private WebElement buttonSendEmail;
    @FindBy(css = "tbody tr[class = 'zA zE'], tr[class = 'zA yO']")
    private List<WebElement> allRowsWithInboxEmails;
    @FindBy(css = "div.ajl.aib.aZ6 div.TK div.TO[data-tooltip = 'Sent']")
    private WebElement linkToSendFolder;
    @FindBy(css = "input[name = 'q']")
    private WebElement inputFilter;
    @FindBy(css = "div.BltHke.nH.oy8Mbf[role = 'main'] tbody tr")
    private List<WebElement> allRowsWithSentEmails;
    @FindBy(css = "td.bq4.xY ul.bqY li:nth-child(2)")
    private WebElement iconDeleteEmail;
    @FindBy(css = "div.ajl.aib.aZ6 div.TK div.TO[data-tooltip = 'Trash']")
    private WebElement linkToTrashFolderENG;
    @FindBy(css = "div.ajl.aib.aZ6 div.TK div.TO[data-tooltip = 'Корзина']")
    private WebElement linkToTrashFolderRUS;
    @FindBy(css = "div.BltHke.nH.oy8Mbf[role = 'main'] tbody tr")
    private List<WebElement> allRowsWithTrashEmails;
    @FindBy(css = "div.G-tF div.Bn")
    private WebElement buttonDeleteEmailForever;

    public void waitUntilLinkToLoggedUserPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LINK_TO_LOGGED_USER)));
    }

    public boolean loggedUserContainsLoginUsername(String loginUsername) {
        return linkToLoggedUser.getAttribute(ATTRIBUTE_LOGGED_USER).contains(loginUsername);
    }

    public void waitUntilMaskPreventingClickDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(MASK_PREVENTING_CLICK)));
    }

    public void waitUntilButtonToDelSelectedAcctPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(BUTTON_TO_DELETE_SELECTED_ACCOUNT)));
    }

    public void waitUntilEmailToSelectAndDeletePresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(EMAIL_TO_SELECT_AND_DELETE)));
    }

    public void waitUntilButtonToConfirmEmailDeletionPresent() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(BUTTON_TO_CONFIRM_EMAIL_DELETION)));
    }

    public LoginPage logout() {
        linkToLoggedUser.click();
        linkToSignOut.click();
        LoginPage loginPage = new LoginPage(driver, wait);
        dropDownWithEmailToExpand.click();
        waitUntilMaskPreventingClickDisappear();
        waitUntilButtonToDelSelectedAcctPresent();
        buttonToDeleteSelectedAccount.click();
        waitUntilEmailToSelectAndDeletePresent();
        emailToSelectAndDelete.click();
        waitUntilButtonToConfirmEmailDeletionPresent();
        buttonToConfirmEmailDeletion.click();
        return loginPage;
    }

    public void sendEmail(String recepientEmail, String emailSubject, String emailMsgBody) {
        linkCreateNewEmail.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(INPUT_EMAIL_TO)));
        inputEmailTo.sendKeys(recepientEmail + MAIL_BOX_DOMAIN);
        inputEmailSubject.sendKeys(emailSubject);
        inputEmailMessageBody.sendKeys(emailMsgBody);
        buttonSendEmail.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
    }

    public boolean checkEmailReceived(String emailSender, String emailSubject, String emailMsgBody) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(HINT_NEW_EMAIL_ARRIVED)));
        boolean isInboxEmailCorrect = false;
        isInboxEmailCorrect = allRowsWithInboxEmails.stream()
                .anyMatch(singleRowWithInboxEmail -> {
                    return ((emailSender + MAIL_BOX_DOMAIN).equals(singleRowWithInboxEmail.findElement(INBOX_EMAIL_RECEPIENT).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                            && (singleRowWithInboxEmail.findElement(INBOX_EMAIL_SUBJECT).getText().equals(emailSubject))
                            && (singleRowWithInboxEmail.findElement(INBOX_EMAIL_MSG_BODY).getText().replaceAll(".+\n", "").contains(emailMsgBody)));
                });
        return isInboxEmailCorrect;
    }

    public boolean checkSentFolder(String emailRecipient, String emailSubject, String emailMsgBody) {
        linkToSendFolder.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return inputFilter.getAttribute(ATTRIBUTE_NAME_INPUT_FILTER_TO_COMPARE_WITH).equals(ATTRIBUTE_VALUE_FILTER_BY_SENT);
            }
        });
        boolean isSentEmailCorrect = false;
        isSentEmailCorrect = allRowsWithSentEmails.stream()
                .anyMatch(singleRowWithSentEmail -> {
                    return ((emailRecipient + MAIL_BOX_DOMAIN).equals(singleRowWithSentEmail.findElement(SENT_EMAIL_RECEPIENT).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                            && (singleRowWithSentEmail.findElement(SENT_EMAIL_SUBJECT).getText().equals(emailSubject))
                            && (singleRowWithSentEmail.findElement(SENT_EMAIL_MSG_BODY).getText().replaceAll(".+\n", "").contains(emailMsgBody)));
                });
        return isSentEmailCorrect;
    }

    public void deleteEmailFromInbox(String emailRecipient, String emailSubject, String emailMsgBody) {
        WebElement singleRowWithReceivedEmailToDelete = allRowsWithInboxEmails.stream()
                .filter(singleRowWithReceivedEmail -> (emailRecipient + MAIL_BOX_DOMAIN).equals(singleRowWithReceivedEmail.findElement(INBOX_EMAIL_RECEPIENT_SENT_TO_SELF).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                        && (singleRowWithReceivedEmail.findElement(INBOX_EMAIL_SUBJECT_SENT_TO_SELF).getText().equals(emailSubject))
                        && (singleRowWithReceivedEmail.findElement(INBOX_EMAIL_MSG_BODY_SENT_TO_SELF).getText().replaceAll(".+\n", "").contains(emailMsgBody)))
                .findFirst()
                .get();
        Actions action = new Actions(driver);
        action.moveToElement(singleRowWithReceivedEmailToDelete).moveToElement(iconDeleteEmail).click().build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
    }

    public boolean checkTrashFolder(String emailRecipient, String emailSubject, String emailMsgBody) {
        linkToTrashFolderENG.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return inputFilter.getAttribute(ATTRIBUTE_NAME_INPUT_FILTER_TO_COMPARE_WITH).equals(ATTRIBUTE_VALUE_FILTER_BY_TRASH);
            }
        });
        Actions action = new Actions(driver);
        action.moveToElement(inputFilter).perform();
        boolean isTrashEmailCorrect = false;
        isTrashEmailCorrect = allRowsWithTrashEmails.stream()
                .anyMatch(singleRowWithTrashEmail -> {
                    return ((emailRecipient + MAIL_BOX_DOMAIN).equals(singleRowWithTrashEmail.findElement(TRASH_EMAIL_RECEPIENT).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                            && (singleRowWithTrashEmail.findElement(TRASH_EMAIL_SUBJECT).getText().equals(emailSubject))
                            && (singleRowWithTrashEmail.findElement(TRASH_EMAIL_MSG_BODY).getText().replaceAll(".+\n", "").contains(emailMsgBody)));
                });
        return isTrashEmailCorrect;
    }

    public void deleteEmailFromTrashForever(String currentUser, String emailRecipient, String emailSubject, String emailMsgBody) {
        if (currentUser.equals("seleniumtests10")) {
            linkToTrashFolderENG.click();
        } else {
            linkToTrashFolderRUS.click();
        }
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return inputFilter.getAttribute(ATTRIBUTE_NAME_INPUT_FILTER_TO_COMPARE_WITH).equals(ATTRIBUTE_VALUE_FILTER_BY_TRASH);
            }
        });
        Actions action = new Actions(driver);
        action.moveToElement(inputFilter).perform();
        WebElement singleRowWithTrashEmailToDelete = allRowsWithTrashEmails.stream()
                .filter(singleRowWithTrashEmail -> (emailRecipient + MAIL_BOX_DOMAIN).equals(singleRowWithTrashEmail.findElement(TRASH_EMAIL_RECEPIENT).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                        && (singleRowWithTrashEmail.findElement(TRASH_EMAIL_SUBJECT).getText().equals(emailSubject))
                        && (singleRowWithTrashEmail.findElement(TRASH_EMAIL_MSG_BODY).getText().replaceAll(".+\n", "").contains(emailMsgBody)))
                .findFirst()
                .get();
        singleRowWithTrashEmailToDelete.findElement(CHECKBOX_TO_SELECT_EMAIL).click();
        buttonDeleteEmailForever.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
    }

    public void deleteEmailFromSent(String emailRecipient, String emailSubject, String emailMsgBody) {
        linkToSendFolder.click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return inputFilter.getAttribute(ATTRIBUTE_NAME_INPUT_FILTER_TO_COMPARE_WITH).equals(ATTRIBUTE_VALUE_FILTER_BY_SENT);
            }
        });
        Actions action = new Actions(driver);
        action.moveToElement(inputFilter).perform();
        WebElement singleRowWithSentEmailToDelete = allRowsWithSentEmails.stream()
                .filter(singleRowWithSentEmail -> (emailRecipient + MAIL_BOX_DOMAIN).equals(singleRowWithSentEmail.findElement(SENT_EMAIL_RECEPIENT).getAttribute(ATTRIBUTE_EMAIL_RECEPIENT_TO_COMPARE_WITH))
                        && (singleRowWithSentEmail.findElement(SENT_EMAIL_SUBJECT).getText().equals(emailSubject))
                        && (singleRowWithSentEmail.findElement(SENT_EMAIL_MSG_BODY).getText().replaceAll(".+\n", "").contains(emailMsgBody)))
                .findFirst()
                .get();
        action.moveToElement(singleRowWithSentEmailToDelete).moveToElement(singleRowWithSentEmailToDelete.findElement(By.cssSelector("td.bq4.xY ul.bqY li:nth-child(2)"))).click().build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TOOLTIP_ACTION_SUCCESSFUL)));
    }
}