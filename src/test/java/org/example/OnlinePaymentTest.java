package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
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
    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("\n========== ТЕСТЫ ЗАВЕРШЕНЫ ==========");
        }
    }

    @Test
    @Order(1)
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
            paymentPage.selectServiceType(service);
            String[] placeholders = paymentPage.getPlaceholdersForService();

            System.out.println("\nТариф: " + service);
            for (int j = 0; j < expectedPlaceholders[i].length; j++) {
                assertEquals(expectedPlaceholders[i][j], placeholders[j],
                        "Плейсхолдер не совпадает для " + service + ": " + expectedPlaceholders[i][j]);
            }
        }
        System.out.println("✓ Проверка плейсхолдеров всех тарифов завершена");
    }

    @Test
    @Order(2)
    @DisplayName("Заполнение формы 'Услуги связи' и проверка модального окна")
    public void testPaymentFormAndModal() {
        paymentPage.selectServiceType("Услуги связи");
        String testPhone = "297777777";
        String testAmount = "100";

        paymentPage.enterPhoneNumber(testPhone);
        paymentPage.enterAmount(testAmount);

        // Проверяем сумму на радиокнопке
        double radioAmount = paymentPage.getAmountOnRadioButton();
        assertEquals(Double.parseDouble(testAmount), radioAmount,
                "Сумма на радиокнопке не совпадает");

        paymentPage.clickContinueButton();

        // Проверяем модальное окно
        boolean modalVisible = paymentPage.switchToPaymentIframe();
        assertTrue(modalVisible, "Модальное окно не появилось");

        double modalAmount = paymentPage.getAmountInModal();
        assertEquals(Double.parseDouble(testAmount), modalAmount, 0.01,
                "Сумма в модальном окне не совпадает");

        String phoneInModal = paymentPage.getPhoneInModal();
        assertTrue(phoneInModal.contains("297777777"), "Телефон в модальном окне не совпадает");

        // Проверка полей карты
        assertTrue(paymentPage.areCardFieldsDisplayed(), "Поля карты не отображаются");

        // Возврат на основную страницу
        paymentPage.switchToDefaultContent();
        System.out.println("✓ Проверка формы 'Услуги связи' и модального окна завершена");
    }

    @Test
    @Order(3)
    @DisplayName("Проверка названия блока")
    public void testBlockTitle() {
        String title = paymentPage.getBlockTitle();
        assertTrue(title.contains("Онлайн пополнение"), "Заголовок блока не найден");
        System.out.println("✓ Заголовок блока: " + title);
    }

    @Test
    @Order(4)
    @DisplayName("Проверка логотипов платежных систем")
    public void testPaymentLogos() {
        int logosCount = paymentPage.getPaymentLogosCount();
        assertTrue(logosCount > 0, "Логотипы платежных систем отсутствуют");
        assertTrue(paymentPage.arePaymentLogosDisplayed(), "Логотипы платежных систем не отображаются");
        System.out.println("✓ Логотипы платежных систем проверены");
    }

    @Test
    @Order(5)
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    public void testDetailsLink() {
        String initialUrl = paymentPage.getCurrentUrl();
        paymentPage.clickDetailsLink();
        String currentUrl = paymentPage.getCurrentUrl();
        assertNotEquals(initialUrl, currentUrl, "Ссылка не сработала");
        assertTrue(currentUrl.contains("poryadok-oplaty") || currentUrl.contains("help"), "Ссылка ведет не на ту страницу");
        paymentPage.navigateBack();
        System.out.println("✓ Ссылка 'Подробнее о сервисе' проверена");
    }

    @Test
    @Order(6)
    @DisplayName("Проверка кнопки 'Продолжить' и открытия iframe")
    public void testContinueButtonOpensIframe() {
        String testPhone = "297777777";
        String testAmount = "100";

        paymentPage.selectServiceType("Услуги связи");
        paymentPage.enterPhoneNumber(testPhone);
        paymentPage.enterAmount(testAmount);
        paymentPage.clickContinueButton();

        boolean iframeVisible = paymentPage.isPaymentIframeVisible();
        assertTrue(iframeVisible, "После нажатия 'Продолжить' iframe модального окна не появился");

        System.out.println("✓ Кнопка 'Продолжить' работает, iframe модального окна отображается");

        paymentPage.switchToDefaultContent();
    }

}







