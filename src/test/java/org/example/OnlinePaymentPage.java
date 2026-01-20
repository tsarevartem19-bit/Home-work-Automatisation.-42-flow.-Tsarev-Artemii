package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OnlinePaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;


    private static final String BASE_URL = "https://www.mts.by/";


    private static final By BLOCK_TITLE = By.xpath("//*[contains(text(), 'Онлайн пополнение')]");


    private static final By PAYMENT_LOGOS = By.xpath("//img[contains(@src, '/pay/')]");


    private static final By DETAILS_LINK = By.xpath("//a[@href=\"/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/\"]");


    private static final By SERVICE_CONNECTION = By.xpath("//*[contains(text(), 'Услуги связи')]");
    private static final By SERVICE_INTERNET = By.xpath("//*[contains(text(), 'Домашний интернет')]");
    private static final By SERVICE_INSTALLMENT = By.xpath("//*[contains(text(), 'Рассрочка')]");
    private static final By SERVICE_DEBT = By.xpath("//*[contains(text(), 'Задолженность')]");


    private static final By PHONE_INPUT = By.id("connection-phone");
    private static final By AMOUNT_INPUT = By.id("connection-sum");

    private static final By CONTINUE_BUTTON = By.xpath("//button[contains(text(), 'Продолжить')]");

    private static final By PAYMENT_IFRAME = By.xpath("//iframe[contains(@src, 'checkout.bepaid.by')]");

    private static final By COOKIES_BUTTON = By.xpath("//button[contains(text(), 'Принять') or contains(text(), 'Согласен')]");

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void openSite() {
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void acceptCookies() {
        try {
            Thread.sleep(1000);
            List<WebElement> cookieButtons = driver.findElements(COOKIES_BUTTON);
            if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
                cookieButtons.get(0).click();
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getBlockTitle() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
            String text = title.getText().trim();
            System.out.println("DEBUG: Найден текст заголовка: '" + text + "'");
            return text;
        } catch (TimeoutException e) {
            return "Заголовок не найден";
        }
    }

    public int getPaymentLogosCount() {
        try {
            List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);
            System.out.println("DEBUG: Найдено логотипов: " + logos.size());
            return logos.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean arePaymentLogosDisplayed() {
        try {
            List<WebElement> logos = driver.findElements(PAYMENT_LOGOS);
            boolean displayed = !logos.isEmpty() && logos.get(0).isDisplayed();
            System.out.println("DEBUG: Логотипы отображаются: " + displayed);
            return displayed;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDetailsLink() {
        try {
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
            link.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Ссылка не найдена: " + e.getMessage());
        }
    }

    public void selectServiceType(String serviceType) {
        try {
            System.out.println("DEBUG: Выбираем услугу: " + serviceType);

            By serviceLocator;
            switch (serviceType) {
                case "Услуги связи":
                    serviceLocator = SERVICE_CONNECTION;
                    break;
                case "Домашний интернет":
                    serviceLocator = SERVICE_INTERNET;
                    break;
                case "Рассрочка":
                    serviceLocator = SERVICE_INSTALLMENT;
                    break;
                case "Задолженность":
                    serviceLocator = SERVICE_DEBT;
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип услуги: " + serviceType);
            }

            List<WebElement> serviceElements = driver.findElements(serviceLocator);
            System.out.println("DEBUG: Найдено элементов с текстом '" + serviceType + "': " + serviceElements.size());

            if (!serviceElements.isEmpty()) {
                // Ищем кликабельный элемент (не заголовок, не текст где-то еще)
                for (WebElement element : serviceElements) {
                    try {
                        if (element.isDisplayed() && element.isEnabled()) {
                            System.out.println("DEBUG: Кликаем на элемент: " + element.getTagName() + " с классом: " + element.getAttribute("class"));
                            element.click();
                            Thread.sleep(1000);
                            return;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                }
            }

            try {
                WebElement radio = driver.findElement(By.xpath("//input[@type='radio' and @value='" + serviceType.toLowerCase() + "']/.."));
                radio.click();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("DEBUG: Радиокнопка не найдена");
            }

        } catch (Exception e) {
            System.out.println("Ошибка выбора услуги '" + serviceType + "': " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        try {
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
            phoneField.clear();
            phoneField.sendKeys(phoneNumber);
            Thread.sleep(300);
            System.out.println("DEBUG: Введен номер: " + phoneNumber);
        } catch (Exception e) {
            System.out.println("Поле телефона не найдено: " + e.getMessage());
        }
    }

    public void enterAmount(String amount) {
        try {
            WebElement amountField = wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_INPUT));
            amountField.clear();
            amountField.sendKeys(amount);
            Thread.sleep(300);
            System.out.println("DEBUG: Введена сумма: " + amount);
        } catch (Exception e) {
            System.out.println("Поле суммы не найдено: " + e.getMessage());
        }
    }

    public void clickContinueButton() {
        try {
            System.out.println("DEBUG: Ищем кнопку 'Продолжить'");
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BUTTON));
            System.out.println("DEBUG: Кнопка найдена, кликаем");
            continueBtn.click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Кнопка не найдена: " + e.getMessage());
            // Попробуем найти кнопку по классу
            try {
                List<WebElement> buttons = driver.findElements(By.cssSelector("button[type='submit']"));
                if (!buttons.isEmpty()) {
                    buttons.get(0).click();
                    Thread.sleep(3000);
                }
            } catch (Exception ex) {
                System.out.println("Альтернативная кнопка тоже не найдена");
            }
        }
    }


    public boolean switchToPaymentIframe() {
        try {
            System.out.println("DEBUG: Ищем iframe оплаты");
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_IFRAME));
            System.out.println("DEBUG: Iframe найден, переключаемся");
            driver.switchTo().frame(iframe);
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Iframe не найден: " + e.getMessage());
            return false;
        }
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public boolean isPaymentModalVisible() {
        try {
            List<WebElement> iframes = driver.findElements(PAYMENT_IFRAME);
            boolean visible = !iframes.isEmpty() && iframes.get(0).isDisplayed();
            System.out.println("DEBUG: Модальное окно видимо: " + visible);
            return visible;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateBack() {
        driver.navigate().back();
        waitForPageLoad();
    }

    public void waitForPageLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void refreshPage() {
        driver.navigate().refresh();
        waitForPageLoad();
        acceptCookies();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }


    public boolean isElementPresent(By locator) {
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void printDebugInfo() {
        System.out.println("=== DEBUG INFO ===");
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Заголовок страницы: " + driver.getTitle());

        System.out.println("Заголовок блока присутствует: " + isElementPresent(BLOCK_TITLE));
        System.out.println("Поле телефона присутствует: " + isElementPresent(PHONE_INPUT));
        System.out.println("Поле суммы присутствует: " + isElementPresent(AMOUNT_INPUT));
        System.out.println("Кнопка Продолжить присутствует: " + isElementPresent(CONTINUE_BUTTON));

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String service : services) {
            List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + service + "')]"));
            System.out.println("Элементов с текстом '" + service + "': " + elements.size());
            if (!elements.isEmpty()) {
                System.out.println("  Первый элемент: tag=" + elements.get(0).getTagName() +
                        ", class=" + elements.get(0).getAttribute("class"));
            }
        }
        System.out.println("==================");
    }
}






