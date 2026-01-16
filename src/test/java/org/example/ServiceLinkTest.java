package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceLinkTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Явное ожидание
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://mts.by");
    }

    @Test
    void checkServiceLink() {


        wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))).click();


        try {
            WebElement chatWidget = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".webim-button-corner")));
            chatWidget.click();
        } catch (Exception e) {

        }


        WebElement detailsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(@href,'poryadok-oplaty')]"))
        );


        detailsLink.click();


        wait.until(ExpectedConditions.urlContains("poryadok-oplaty"));


        String currentUrl = driver.getCurrentUrl();
        assertTrue(
                currentUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"),
                "Переход по ссылке не произошёл"
        );
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}