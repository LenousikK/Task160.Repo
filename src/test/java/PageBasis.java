import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageBasis {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public PageBasis (WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
}