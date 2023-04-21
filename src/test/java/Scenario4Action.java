import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class Scenario4Action {
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
    public void click_chat_input_and_send_verify_message() throws InterruptedException, AWTException {
        driver.manage().window().maximize();
        driver.navigate().to("https://phptravels.com/");

        //switch to iframe
        driver.switchTo().frame("hubspot-conversations-iframe");
        //click chat support
        WebElement btnChatWithSupport = driver.findElement(By.xpath("//div[@class='VizExNotificationBadge__Wrapper-k73cw3-0 fUOpTB']"));
        btnChatWithSupport.click();

        //input text to chatbox
        WebElement divTextBox = driver.findElement(By.xpath("//div[@class='VizExExpandingInput__StyledInput-s3si2f-0 kwVNkk widget-textarea message-box']"));
        divTextBox.sendKeys("test message");
        //send message
        WebElement btnSend = driver.findElement(By.xpath("//button[@class='VizExIconButton__AbstractVizExIconButton-sc-1a9k5ji-0 hKexAa chat-send-button']"));
        btnSend.click();
        //click button attach file
        WebElement btnAttachFile = driver.findElement(By.xpath("//button[@class='VizExIconButton__AbstractVizExIconButton-sc-1a9k5ji-0 hKexAa']"));
        btnAttachFile.click();
        //upload file
        String pathFile = "C:\\Users\\jv\\Desktop\\test.txt";
        StringSelection selection = new StringSelection(pathFile);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //send file
        btnSend.click();

        WebElement lastFileName = driver.findElement(By.xpath("(//div[@class='VizExFileAttachment__FileInfoWrapper-t08vtt-3 iXiRCa']//b)[last()]"));
        Assert.assertTrue(lastFileName.getText().contains("test"));
    }

    @AfterTest
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.quit();
    }
}
