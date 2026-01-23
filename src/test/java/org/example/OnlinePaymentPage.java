package org.example;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class OnlinePaymentPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by/";
    private static final By BLOCK_TITLE = By.xpath("//*[contains(text(), 'Онлайн пополнение')]");
    private static final By PAYMENT_LOGOS = By.xpath("//img[contains(@src, '/pay/')]");
    private static final By DETAILS_LINK = By.xpath("//a[contains(@href,'poryadok-oplaty') or contains(@href,'help')]");
    private static final By COOKIES_BUTTON = By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]");
    private static final By SERVICE_SELECT = By.cssSelector(".select__header");
    private static final By CONTINUE_BUTTON = By.xpath("//button[contains(text(), 'Продолжить')]");
    private static final By PAYMENT_IFRAME = By.xpath("//iframe[contains(@src, 'checkout.bepaid.by')]");

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открываем сайт {BASE_URL}")
    public void openSite() {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Step("Принимаем cookies")
    public void acceptCookies() {
        try {
            List<WebElement> cookieButtons = driver.findElements(COOKIES_BUTTON);
            if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
                cookieButtons.get(0).click();
                Thread.sleep(500);
            }
        } catch (Exception e) {
            // Игнорируем, если кнопка не найдена
        }
    }

    @Step("Выбираем услугу '{serviceType}'")
    public void selectServiceType(String serviceType) {
        try {
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(SERVICE_SELECT));
            selectHeader.click();
            Thread.sleep(300);

            WebElement option = driver.findElement(By.xpath("//p[contains(@class,'select__option') and text()='" + serviceType + "']"));
            option.click();
            Thread.sleep(500);
        } catch (Exception e) {
            attachText("Ошибка выбора услуги", e.getMessage());
        }
    }

    @Step("Получаем плейсхолдеры для текущей услуги")
    public String[] getPlaceholdersForService() {
        List<String> placeholders = new ArrayList<>();
        try {
            List<WebElement> inputs = driver.findElements(By.cssSelector("input[placeholder]"));
            for (WebElement input : inputs) {
                String placeholder = input.getAttribute("placeholder").trim();
                if (!placeholder.isEmpty()) {
                    placeholders.add(placeholder);
                }
            }
        } catch (Exception e) {
            attachText("Ошибка получения плейсхолдеров", e.getMessage());
        }
        return placeholders.toArray(new String[0]);
    }

    @Step("Вводим телефон '{phoneNumber}'")
    public void enterPhoneNumber(String phoneNumber) {
        try {
            WebElement phoneField = driver.findElement(By.cssSelector("input[placeholder*='Номер']"));
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
        } catch (Exception e) {
            attachText("Ошибка ввода телефона", e.getMessage());
        }
    }

    @Step("Вводим сумму '{amount}'")
    public void enterAmount(String amount) {
        try {
            WebElement amountField = driver.findElement(By.cssSelector("input[placeholder='Сумма']"));
            amountField.clear();
            amountField.sendKeys(amount);
        } catch (Exception e) {
            attachText("Ошибка ввода суммы", e.getMessage());
        }
    }

    @Step("Получаем сумму на радиокнопке")
    public double getAmountOnRadioButton() {
        try {
            WebElement radioAmount = driver.findElement(By.xpath("//button[contains(@class,'colored')]/span"));
            String text = radioAmount.getText().replaceAll("[^0-9.,]", "").replace(",", ".");
            return Double.parseDouble(text);
        } catch (Exception e) {
            attachText("Ошибка получения суммы с радиокнопки", e.getMessage());
            return 0;
        }
    }

    @Step("Нажимаем кнопку 'Продолжить'")
    public void clickContinueButton() {
        try {
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
            continueBtn.click();
            Thread.sleep(1500);
        } catch (Exception e) {
            attachText("Ошибка нажатия кнопки 'Продолжить'", e.getMessage());
        }
    }

    @Step("Проверяем видимость iframe модального окна")
    public boolean isPaymentIframeVisible() {
        try {
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_IFRAME));
            return iframe.isDisplayed();
        } catch (Exception e) {
            attachText("Ошибка проверки iframe", e.getMessage());
            return false;
        }
    }

    @Step("Переключаемся на iframe модального окна")
    public boolean switchToPaymentIframe() {
        try {
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_IFRAME));
            driver.switchTo().frame(iframe);
            Thread.sleep(500);
            return true;
        } catch (Exception e) {
            attachText("Ошибка переключения на iframe", e.getMessage());
            return false;
        }
    }

    @Step("Возвращаемся в основной контент")
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    @Step("Получаем сумму из модального окна")
    public double getAmountInModal() {
        try {
            WebElement modalAmount = driver.findElement(By.cssSelector("span.ng-star-inserted"));
            String text = modalAmount.getText().replaceAll("[^0-9.,]", "").replace(",", ".");
            return Double.parseDouble(text);
        } catch (Exception e) {
            attachText("Ошибка получения суммы в модальном окне", e.getMessage());
            return 0;
        }
    }

    @Step("Получаем телефон из модального окна")
    public String getPhoneInModal() {
        try {
            WebElement phoneSpan = driver.findElement(By.xpath("//span[contains(text(),'Номер')]"));
            String text = phoneSpan.getText();
            return text.replaceAll("[^0-9]", ""); // только цифры
        } catch (Exception e) {
            attachText("Ошибка получения телефона из модального окна", e.getMessage());
            return "";
        }
    }

    @Step("Проверяем отображение полей карты")
    public boolean areCardFieldsDisplayed() {
        try {
            return driver.findElement(By.id("cc-number")).isDisplayed() &&
                    driver.findElement(By.cssSelector("input[formcontrolname='expirationDate']")).isDisplayed() &&
                    driver.findElement(By.cssSelector("input[formcontrolname='cvc']")).isDisplayed();
        } catch (Exception e) {
            attachText("Ошибка проверки полей карты", e.getMessage());
            return false;
        }
    }

    @Step("Получаем заголовок блока")
    public String getBlockTitle() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
            return title.getText().trim();
        } catch (Exception e) {
            attachText("Ошибка получения заголовка блока", e.getMessage());
            return "Заголовок не найден";
        }
    }

    @Step("Получаем количество логотипов платежных систем")
    public int getPaymentLogosCount() {
        try {
            List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);
            return logos.size();
        } catch (Exception e) {
            attachText("Ошибка получения логотипов", e.getMessage());
            return 0;
        }
    }

    @Step("Проверяем отображение логотипов платежных систем")
    public boolean arePaymentLogosDisplayed() {
        try {
            List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);
            return !logos.isEmpty() && logos.get(0).isDisplayed();
        } catch (Exception e) {
            attachText("Ошибка проверки логотипов", e.getMessage());
            return false;
        }
    }

    @Step("Нажимаем ссылку 'Подробнее о сервисе'")
    public void clickDetailsLink() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
            link.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            attachText("Ошибка клика по ссылке 'Подробнее'", e.getMessage());
        }
    }

    @Step("Получаем текущий URL")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Навигация назад")
    public void navigateBack() {
        driver.navigate().back();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    // Добавляем текстовую аннотацию в Allure
    @Attachment(value = "{name}", type = "text/plain")
    private String attachText(String name, String message) {
        return message;
    }
}











