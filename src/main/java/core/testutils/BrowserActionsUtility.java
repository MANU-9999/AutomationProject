package core.testutils;

import core.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class BrowserActionsUtility {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LoggerUtility.getLogger(BrowserActionsUtility.class);
    private WebDriverWait wait;

    // Static factory method to create and get driver instance
    public static WebDriver getDriver(Browser browser, boolean isHeadless) {
        if (driver.get() == null) {
            WebDriver newDriver = createDriver(browser, isHeadless);
            driver.set(newDriver);
        }
        return driver.get();
    }

    // Create driver based on browser type
    private static WebDriver createDriver(Browser browser, boolean isHeadless) {
        WebDriver newDriver;
        switch (browser) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless", "--window-size=1920x1080");
                }
                newDriver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                newDriver = new FirefoxDriver(firefoxOptions);
                break;
            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) {
                    edgeOptions.addArguments("--headless", "--window-size=1920x1080");
                }
                newDriver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return newDriver;
    }

    // Get current driver instance
    public static WebDriver getCurrentDriver() {
        return driver.get();
    }

    // Quit and cleanup driver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    // Constructor for page objects
    public BrowserActionsUtility() {
        this.wait = new WebDriverWait(getCurrentDriver(), Duration.ofSeconds(30L));
    }

    // Common Selenium actions
    public void navigateToWebsite(String url) {
        logger.info("Navigating to URL: {}", url);
        getCurrentDriver().manage().window().maximize();
        getCurrentDriver().get(url);
    }

    public void clickOn(By locator) {
        logger.debug("Clicking element: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void enterText(By locator, String input) {
        logger.debug("Entering text '{}' in element: {}", input, locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(input);
    }

    public String getVisibleText(By locator) {
        logger.debug("Getting text from element: {}", locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }
    public void waitTill(int duration) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(duration));
    }
    public void clickOnWebElement(WebElement locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        logger.info("Element found and now performing click");
        element.click();
    }
    public void rightClick(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions = new Actions(driver.get());
        logger.info("Element found and now performing right click");
        actions.moveToElement(element).contextClick().build().perform();
    }

    public void doubleClick(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions = new Actions(driver.get());
        logger.info("Element found and now performing double click");
        actions.moveToElement(element).doubleClick().build().perform();
    }

    public WebElement webElement(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now performing click");
        return element;
    }


    // Clear text in a field using locator
    public void clearText(By textBoxLocator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element found and clearing the text box");
        element.clear();
    }

    // Select an option from a dropdown using visible text
    public void selectFromDropdown(By dropDownLocator, String optionToSelect) {
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        select.selectByVisibleText(optionToSelect);
    }

    public void selectDate(By locator, String dateValue) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //dateField.click();
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].value = arguments[1];", dateField, dateValue);
    }


    public List<WebElement> getAttribute(By locator) {
        List<WebElement> linkElements = driver.get().findElements(locator);
        return linkElements;
    }

    public void getWindowHandles(String windowName) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10l));
        String mainWindowHandle = driver.get().getWindowHandle();
        Set<String> windowHandles = driver.get().getWindowHandles();

        for (String window : windowHandles) {
            driver.get().switchTo().window(window);
            String title = driver.get().getTitle();
            assert title != null;

            if (title.contains(windowName)) {
                System.out.println(title);
                break;
            }
        }
        driver.get().close();
        driver.get().switchTo().window(mainWindowHandle);

    }

    public void handleAlert(String type, String text) {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        logger.info("Handling alert of type: {}", type);

        switch (type.toLowerCase()) {
            case "accept" -> alert.accept();
            case "send" -> {
                if (text != null) {
                    alert.sendKeys(text);
                    alert.accept();
                } else throw new IllegalArgumentException("Text is required for sending to alert.");
            }
            default -> throw new IllegalArgumentException("Unsupported alert type: " + type);
        }
    }


    public void iframeActivity(String iframeBy, Object value, Boolean defaultFrame) {
        waitTill(10);
        switch (iframeBy) {
            case "index":
                driver.get().switchTo().frame((Integer) value);
                break;
            case "nameOrId":
                driver.get().switchTo().frame((String) value);
                break;
            case "webElement":
                driver.get().switchTo().frame((WebElement) value);
                break;
            case "byElement":
                WebElement iframeElement = driver.get().findElement((By) value);
                driver.get().switchTo().frame(iframeElement);
                break;
            default:
                throw new IllegalArgumentException("Invalid iframeBy: " + iframeBy);
        }

        if (Boolean.TRUE.equals(defaultFrame)) {
            driver.get().switchTo().defaultContent();
        }
    }

    public void defaultIframe() {
        driver.get().switchTo().defaultContent();
    }

    public static String captureScreenshot(String testName) {
        try {
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            File screenshotFile = new File(screenshotPath);
            FileUtils.copyFile(((TakesScreenshot) getCurrentDriver()).getScreenshotAs(OutputType.FILE), screenshotFile);
            return screenshotPath;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    public void brokenLinkVerification(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        List<WebElement> links = driver.get().findElements(locator);
//        for(int i=0;i<links.size();i++){
//            WebElement link=links.get(i);

        for (WebElement link : links) {
            String url = link.getDomAttribute("href");
            verifyLink(url);
        }
    }

    public void verifyLink(String linkURL) {
        try {
            URL url = new URL(linkURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode > 400) {
                System.out.println(linkURL + "-" + httpURLConnection.getResponseCode() + " is a broken link");
            } else {
                System.out.println(linkURL + "-" + httpURLConnection.getResponseCode());
            }
        } catch (Exception ignored) {
        }
    }

    public void retryMethodForPages(By locator) {
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                clickOn(locator);
                break;
            } catch (Exception e) {
                attempt++;
                if (attempt == maxAttempts) {
                    throw e;
                }
                waitTill(2);
            }
        }
    }

    // ... rest of the existing code ...
    public List<Map<String, String>> readExcelData() throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/core/testdata/Excel_Imports/Credentials.xlsx";

        List<Map<String, String>> credentials = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(path))) {
            XSSFSheet sheet = workbook.getSheet("sheet1");
            XSSFRow headerRow = sheet.getRow(0);
            int columns = headerRow.getLastCellNum();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> userData = new HashMap<>();
                for (int j = 0; j < columns; j++) {
                    XSSFCell keyCell = headerRow.getCell(j);
                    XSSFCell valueCell = row.getCell(j);
                    if (keyCell != null && valueCell != null) {
                        userData.put(keyCell.getStringCellValue().trim(), valueCell.getStringCellValue().trim());
                    }
                }

                if (!userData.isEmpty()) {
                    credentials.add(userData);
                }
            }

        } catch (Exception e) {
            System.out.println("Error while reading the file" + e.getMessage());
        }
        return credentials;
    }
}
