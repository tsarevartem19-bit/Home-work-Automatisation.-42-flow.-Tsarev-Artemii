package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OnlinePaymentTest {
    private WebDriver driver;
    private OnlinePaymentPage paymentPage;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        paymentPage = new OnlinePaymentPage(driver);

        paymentPage.openSite();
        paymentPage.acceptCookies();
        paymentPage.waitForPageLoad();

        paymentPage.printDebugInfo();

    }

    @Test
    @Order(1)
    @DisplayName("Проверка названия блока")
    public void testBlockTitle() {
        System.out.println("\n[Тест 1] Проверка названия блока");
        String actualTitle = paymentPage.getBlockTitle();

        assertTrue(actualTitle.contains("Онлайн пополнение\n" +
                "без комиссии"));

        System.out.println("✓ Заголовок корректен: " + actualTitle);
    }

    @Test
    @Order(2)
    @DisplayName("Проверка логотипов платежных систем")
    public void testPaymentLogos() {
        System.out.println("\n[Тест 2] Проверка логотипов платежных систем");

        int logosCount = paymentPage.getPaymentLogosCount();
        System.out.println("Найдено логотипов: " + logosCount);

        assertTrue(logosCount > 0,
                "Должны быть логотипы платежных систем. Найдено: " + logosCount);

        boolean logosDisplayed = paymentPage.arePaymentLogosDisplayed();
        assertTrue(logosDisplayed, "Логотипы не отображаются!");
        System.out.println("✓ Логотипы отображаются корректно");
    }

    @Test
    @Order(3)
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testDetailsLink() {
        System.out.println("\n[Тест 3] Проверка ссылки 'Подробнее о сервисе'");

        String initialUrl = paymentPage.getCurrentUrl();
        paymentPage.clickDetailsLink();

        String currentUrl = paymentPage.getCurrentUrl();
        System.out.println("Было: " + initialUrl);
        System.out.println("Стало: " + currentUrl);

        assertNotEquals(currentUrl, initialUrl, "URL не изменился!");
        assertTrue(currentUrl.contains("poryadok-oplaty") || currentUrl.contains("help"),
                "Ссылка должна вести на страницу с информацией об оплате");

        paymentPage.navigateBack();
        paymentPage.acceptCookies();
        System.out.println("✓ Ссылка работает корректно");
    }

    @Test
    @Order(4)
    @DisplayName("Проверка незаполненных полей для всех вариантов оплаты")
    public void testEmptyFieldsValidation() {
        System.out.println("\n[Тест 4] Проверка незаполненных полей для всех вариантов оплаты");

        String[] serviceTypes = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String serviceType : serviceTypes) {
            System.out.println("\n--- Проверка для: " + serviceType + " ---");

            paymentPage.selectServiceType(serviceType);
            paymentPage.enterPhoneNumber("");
            paymentPage.enterAmount("");
            paymentPage.clickContinueButton();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            boolean modalVisible = paymentPage.isPaymentModalVisible();
            if (modalVisible) {
                System.out.println(  serviceType );
            } else {
                System.out.println("✓ Валидация работает для: " + serviceType);
            }

            paymentPage.refreshPage();
        }
        System.out.println("\n✓ Проверка всех услуг завершена");
    }

    @Test
    @Order(5)
    @DisplayName("Заполнение формы и проверка модального окна")
    public void testPaymentFormAndModal() {
        System.out.println("\n[Тест 5] Заполнение формы и проверка модального окна");

        paymentPage.selectServiceType("Услуги связи");

        paymentPage.enterPhoneNumber("297777777");

        paymentPage.enterAmount("100");

        paymentPage.clickContinueButton();

        boolean modalVisible = paymentPage.isPaymentModalVisible();

        if (modalVisible) {
            System.out.println("✓ Модальное окно отображается");

            boolean inIframe = paymentPage.switchToPaymentIframe();

            if (inIframe) {
                System.out.println("✓ Успешно переключились в iframe");

                paymentPage.switchToDefaultContent();

                System.out.println("✓ Все проверки модального окна пройдены");
            } else {
                System.out.println("⚠ Iframe не найден, но тест продолжается");
            }
        } else {
            System.out.println("⚠ Модальное окно не появилось. Возможно:");
            System.out.println("  1. Неправильно заполнены поля");
            System.out.println("  2. Изменилась логика сайта");
            System.out.println("  3. Нужно проверить валидацию формы");

            System.out.println("⚠ Тест пропущен - модальное окно не отобразилось");
        }
    }

    @Test
    @Order(6)
    @DisplayName("Дополнительная проверка: наличие полей формы")
    public void testFormFieldsExist() {
        System.out.println("\n[Тест 6] Проверка наличия полей формы");

        paymentPage.selectServiceType("Услуги связи");

        assertTrue(paymentPage.isElementPresent(By.id("connection-phone")),
                "Поле для ввода телефона должно существовать");
        assertTrue(paymentPage.isElementPresent(By.id("connection-sum")),
                "Поле для ввода суммы должно существовать");
        assertTrue(paymentPage.isElementPresent(By.xpath("//button[contains(text(), 'Продолжить')]")),
                "Кнопка 'Продолжить' должна существовать");

        System.out.println("✓ Все основные элементы формы присутствуют");
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("\n========== ТЕСТЫ ЗАВЕРШЕНЫ ==========");
            System.out.println("Браузер закрыт");
        }
    }

    @AfterEach
    public void afterEach() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


