package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PaymentLogosTest {

    WebDriver driver;
    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();


        driver = new ChromeDriver();


        driver.get("https://mts.by");


    }

    @Test
    public void checkPaymentLogos(){
        List<WebElement> logos = driver.findElements(By.xpath("//*[@id='pay-section']//img"));

        for (WebElement logo : logos) {
            assertEquals(true, logo.isDisplayed(), "Логотип не отображается!");
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
