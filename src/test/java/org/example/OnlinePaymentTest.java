package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Онлайн-платежи")
@Feature("Форма оплаты и тарифы")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OnlinePaymentTest {

    private WebDriver driver;
    private OnlinePaymentPage paymentPage;

    @BeforeAll
    @Step("Запуск браузера и открытие сайта")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        paymentPage = new OnlinePaymentPage(driver);

        paymentPage.openSite();
        paymentPage.acceptCookies();
    }

    @AfterAll
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("\n========== ТЕСТЫ ЗАВЕРШЕНЫ ==========");
        }
    }

    @Test
    @Order(1)
    @Story("Проверка плейсхолдеров")
    @DisplayName("Проверка плейсхолдеров для всех тарифов")
    public void testPlaceholdersForAllServices() {
        String[][] expectedPlaceholders = {
                {"Номер телефона", "Сумма", "E-mail для отправки чека"},
                {"Номер абонента", "Сумма", "E-mail для отправки чека"},
                {"Номер счета на 44", "Сумма", "E-mail для отправки чека"},
                {"Номер счета на 2073", "Сумма", "E-mail для отправки чека"}
        };
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (int i = 0; i < services.length; i++) {
            String service = services[i];
            step("Выбираем услугу: " + service, () -> paymentPage.selectServiceType(service));

            String[] placeholders = paymentPage.getPlaceholdersForService();

            for (int j = 0; j < expectedPlaceholders[i].length; j++) {
                String expected = expectedPlaceholders[i][j];
                String actual = placeholders[j];
                step("Сравниваем плейсхолдер: " + expected, () -> assertEquals(expected, actual));
            }
        }
    }

    @Test
    @Order(2)
    @Story("Заполнение формы и модальное окно")
    @DisplayName("Заполнение формы 'Услуги связи' и проверка модального окна")
    public void testPaymentFormAndModal() {
        String testPhone = "297777777";
        String testAmount = "100";

        step("Выбираем услугу 'Услуги связи'", () -> paymentPage.selectServiceType("Услуги связи"));
        step("Вводим телефон", () -> paymentPage.enterPhoneNumber(testPhone));
        step("Вводим сумму", () -> paymentPage.enterAmount(testAmount));

        step("Проверяем сумму на радиокнопке", () -> {
            double radioAmount = paymentPage.getAmountOnRadioButton();
            assertEquals(Double.parseDouble(testAmount), radioAmount,
                    "Сумма на радиокнопке не совпадает");
        });

        step("Нажимаем кнопку 'Продолжить'", () -> paymentPage.clickContinueButton());

        step("Проверяем модальное окно", () -> {
            assertTrue(paymentPage.switchToPaymentIframe(), "Модальное окно не появилось");

            double modalAmount = paymentPage.getAmountInModal();
            assertEquals(Double.parseDouble(testAmount), modalAmount, 0.01,
                    "Сумма в модальном окне не совпадает");

            String phoneInModal = paymentPage.getPhoneInModal();
            assertTrue(phoneInModal.contains(testPhone), "Телефон в модальном окне не совпадает");

            assertTrue(paymentPage.areCardFieldsDisplayed(), "Поля карты не отображаются");

            paymentPage.switchToDefaultContent();
        });
    }

    @Test
    @Order(3)
    @Story("Проверка блока")
    @DisplayName("Проверка названия блока")
    public void testBlockTitle() {
        step("Проверяем заголовок блока", () -> {
            String title = paymentPage.getBlockTitle();
            assertTrue(title.contains("Онлайн пополнение"), "Заголовок блока не найден");
        });
    }

    @Test
    @Order(4)
    @Story("Проверка логотипов")
    @DisplayName("Проверка логотипов платежных систем")
    public void testPaymentLogos() {
        step("Проверяем логотипы платежных систем", () -> {
            assertTrue(paymentPage.getPaymentLogosCount() > 0, "Логотипы платежных систем отсутствуют");
            assertTrue(paymentPage.arePaymentLogosDisplayed(), "Логотипы платежных систем не отображаются");
        });
    }

    @Test
    @Order(5)
    @Story("Проверка ссылки 'Подробнее'")
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testDetailsLink() {
        step("Проверяем ссылку 'Подробнее о сервисе'", () -> {
            String initialUrl = paymentPage.getCurrentUrl();
            paymentPage.clickDetailsLink();
            String currentUrl = paymentPage.getCurrentUrl();
            assertNotEquals(initialUrl, currentUrl, "Ссылка не сработала");
            assertTrue(currentUrl.contains("poryadok-oplaty") || currentUrl.contains("help"),
                    "Ссылка ведет не на ту страницу");
            paymentPage.navigateBack();
        });
    }

    @Test
    @Order(6)
    @Story("Проверка кнопки 'Продолжить'")
    @DisplayName("Проверка кнопки 'Продолжить' и открытия iframe")
    public void testContinueButtonOpensIframe() {
        String testPhone = "297777777";
        String testAmount = "100";

        step("Заполняем форму 'Услуги связи'", () -> {
            paymentPage.selectServiceType("Услуги связи");
            paymentPage.enterPhoneNumber(testPhone);
            paymentPage.enterAmount(testAmount);
        });

        step("Нажимаем кнопку 'Продолжить' и проверяем iframe", () -> {
            paymentPage.clickContinueButton();
            assertTrue(paymentPage.isPaymentIframeVisible(), "iframe модального окна не появился");
            paymentPage.switchToDefaultContent();
        });
    }

    @Step("{step}")
    private void step(String step, Runnable action) {
        try {
            action.run();
        } catch (AssertionError e) {
            takeScreenshot();
            throw e;
        }
    }

    @Attachment(value = "Скриншот ошибки", type = "image/png")
    private byte[] takeScreenshot() {
        return ((org.openqa.selenium.TakesScreenshot) driver)
                .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}







