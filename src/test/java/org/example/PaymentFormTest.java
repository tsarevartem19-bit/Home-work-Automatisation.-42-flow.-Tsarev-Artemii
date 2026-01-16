package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentFormTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://mts.by");
    }

    @Test
    void checkPaymentForm() {

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))).click();

        driver.findElement(By.id("connection-phone"))
                .sendKeys("297777777");

        driver.findElement(By.id("connection-sum"))
                .sendKeys("100");

        WebElement button =
                driver.findElement(By.xpath("//*[@id='pay-connection']/button"));

        button.click();

        WebElement iframe = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe"))
        );

        String iframeSrc = iframe.getAttribute("src");

        assertTrue(
                iframeSrc != null && !iframeSrc.isEmpty(),
                "Iframe оплаты не загрузился"
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
