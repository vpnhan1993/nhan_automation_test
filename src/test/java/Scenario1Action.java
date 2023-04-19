import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Scenario1Action {
    private WebDriver driver;

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void click_btn_integrations_verify_url_agoda_api_integrations() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("https://phptravels.com/");
        Thread.sleep(2000);

        //click button integrations
        WebElement btnIntegrations = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[4]/a"));
        btnIntegrations.click();
        Thread.sleep(2000);

        //hold mouse on Hotels label
        WebElement lblHotels = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[4]/div/div/ul/li[2]/button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(lblHotels).build().perform();
        Thread.sleep(2000);

        //click Agoda label
        WebElement lblAgoda = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/ul/li[4]/div/div/ul/li[8]/ul/li[3]/a"));
        lblAgoda.click();
        Thread.sleep(2000);

        //switch to new window opened by click
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(1));

        //Assert Equal to expect Url
        String expectUrl = "https://phptravels.com/agoda-api-integration";
        Assert.assertEquals(driver.getCurrentUrl(), expectUrl);

        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
