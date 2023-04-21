import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Scenario3Action {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void click_chat_input_and_send_verify_message() {
        driver.manage().window().maximize();
        driver.navigate().to("https://phptravels.com/");

        //switch to iframe
        driver.switchTo().frame("hubspot-conversations-iframe");
        //click chat support
        WebElement btnChatWithSupport = driver.findElement(By.xpath("//div[@class='VizExNotificationBadge__Wrapper-k73cw3-0 fUOpTB']"));
        btnChatWithSupport.click();

        String message = "test message";
        //input text to chatbox
        WebElement divTextBox = driver.findElement(By.xpath("//div[@class='VizExExpandingInput__StyledInput-s3si2f-0 kwVNkk widget-textarea message-box']"));
        divTextBox.sendKeys(message);

        //send message
        WebElement btnSend = driver.findElement(By.xpath("//button[@class='VizExIconButton__AbstractVizExIconButton-sc-1a9k5ji-0 hKexAa chat-send-button']"));
        btnSend.click();

        //verify message
        WebElement lastMessage = driver.findElement(By.xpath("(//div[@data-test-id='primary-message-content'])[last()]"));
        wait.until(ExpectedConditions.textToBePresentInElement(lastMessage, message));
    }

    @AfterTest
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.quit();
    }
}
