import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scenario2Action {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "C:\\Webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void fill_values_valid_to_form_submit_then_verify_text_thank_you() throws InterruptedException {
        driver.manage().window().maximize();
        driver.navigate().to("https://phptravels.com/demo");
        Thread.sleep(2000);

        //fill first name
        WebElement inputFirstName = driver.findElement(By.xpath("/html/body/div[3]/section[1]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/input[1]"));
        inputFirstName.sendKeys("Van Phu");
        Thread.sleep(1000);

        //fill last name
        WebElement inputLastName = driver.findElement(By.xpath("/html/body/div[3]/section[1]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/input[2]"));
        inputLastName.sendKeys("Nhan");
        Thread.sleep(1000);

        //fill business name
        WebElement inputBusinessName = driver.findElement(By.xpath("/html/body/div[3]/section[1]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/input[3]"));
        inputBusinessName.sendKeys("nhan business man");
        Thread.sleep(1000);

        //fill email
        WebElement inputEmail = driver.findElement(By.xpath("/html/body/div[3]/section[1]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/input[4]"));
        inputEmail.sendKeys("nhan@gmail.com");
        Thread.sleep(1000);

        //calculate bypass
        //get Number 1
        WebElement number1Element = driver.findElement(By.id("numb1"));
        String number1 = number1Element.getText();

        //get Number 2
        WebElement number2Element = driver.findElement(By.id("numb2"));
        String number2 = number2Element.getText();

        //calculate result for bypass
        String operation = "+";
        Calculation calculation = new Calculation(operation, number1, number2);
        calculation.doCalculate();
        //send result
        WebElement inputResult = driver.findElement(By.id("number"));
        inputResult.sendKeys(calculation.getResult());
        Thread.sleep(2000);

        //click submit
        WebElement btnSubmit = driver.findElement(By.id("demo"));
        btnSubmit.click();

        //wait for form sent success
        //verify text "Thank you!"
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.xpath("/html/body/div[3]/section[1]/div/div/div[2]/div/div/div/div/div/div/div/div[3]/h2/strong")),
                "Thank you!"));
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
