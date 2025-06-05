package core.testutils;

import core.constants.Browser;
import org.apache.commons.codec.binary.Base32;
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

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();  // ThreadLocal to store WebDriver for each thread
    private static final Logger logger = LoggerUtility.getLogger(BrowserActionsUtility.class);
    private static WebDriverWait wait;

    // Constructor for initializing WebDriver
    public BrowserActionsUtility(WebDriver driver) {
        BrowserActionsUtility.driver.set(driver);  // Assign WebDriver to ThreadLocal
        wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
    }
    // Constructor to initialize WebDriver with a specific browser
    public BrowserActionsUtility(Browser browserName) {
        if (browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());  // Initialize ChromeDriver and set it in ThreadLocal
        } else if (browserName == Browser.FIREFOX) {
            driver.set(new FirefoxDriver());  // Initialize FirefoxDriver and set it in ThreadLocal
        } else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());  // Initialize EdgeDriver and set it in ThreadLocal
        }
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    // Constructor for initializing WebDriver with a specific browser and headless option
    public BrowserActionsUtility(Browser browserName, boolean isHeadless) {
        if (browserName == Browser.CHROME) {
            ChromeOptions options = new ChromeOptions();
            if (isHeadless) {
                options.addArguments("--headless", "--window-size=1920x1080");
            }
            driver.set(new ChromeDriver(options));  // Initialize ChromeDriver with options
        } else if (browserName == Browser.EDGE) {
            EdgeOptions options = new EdgeOptions();
            if (isHeadless) {
                options.addArguments("--headless", "--window-size=1920x1080");
            }
            driver.set(new EdgeDriver(options));  // Initialize EdgeDriver with options
        } else if (browserName == Browser.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            driver.set(new FirefoxDriver(options));  // Initialize FirefoxDriver with options
        }
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
    }

    // Get the WebDriver instance for the current thread
    public WebDriver driver() {
        return driver.get();  // Use ThreadLocal.get() to get the WebDriver for the current thread
    }

    // Navigate to a URL
    public void navigateTo(String url) {
        driver.get().get(url);  // Use WebDriver from ThreadLocal
    }
    // Maximize the browser window
    public void maximizeWindow() {
        driver.get().manage().window().maximize();
    }
    // Click on an element using locator
    public static void clickOn(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now performing click");
        element.click();
    }    public static void clickOnWebElement(WebElement locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        logger.info("Element found and now performing click");
        element.click();
    }
    public static void rightClick(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions=new Actions(driver.get());
        logger.info("Element found and now performing right click");
        actions.moveToElement(element).contextClick().build().perform();
    }
    public static void doubleClick(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Actions actions=new Actions(driver.get());
        logger.info("Element found and now performing double click");
        actions.moveToElement(element).doubleClick().build().perform();
    }
    public static WebElement webElement(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now performing click");
        return element;
    }

    public static void navigateToWebsite(String url) {
        logger.info("Navigating to the website" + url);
        driver.get().get(url);  // Use WebDriver from ThreadLocal
    }

    // Enter text in a field using locator
    public static void enterText(By locator, String textToEnter) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element found and now entering text: " + textToEnter);
        element.sendKeys(textToEnter);
    }

    // Clear text in a field using locator
    public static void clearText(By textBoxLocator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element found and clearing the text box");
        element.clear();
    }

    // Select an option from a dropdown using visible text
    public static void selectFromDropdown(By dropDownLocator, String optionToSelect) {
        WebElement element = driver.get().findElement(dropDownLocator);
        Select select = new Select(element);
        select.selectByVisibleText(optionToSelect);
    }
    public static void selectDate(By locator, String dateValue) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //dateField.click();
        JavascriptExecutor js = (JavascriptExecutor) driver.get();
        js.executeScript("arguments[0].value = arguments[1];", dateField, dateValue);
    }
    // Get visible text from an element
    public static String getVisibleText(By locator) {
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10L));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getText();
    }
    public static List<WebElement> getAttribute(By locator) {
        List<WebElement> linkElements = driver.get().findElements(locator);
        return linkElements;
    }

    public static void getWindowHandles(String windowName){
        wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10l));
       // WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
        Set<String> windowHandles=driver.get().getWindowHandles();

        for(String window:windowHandles){
            driver.get().switchTo().window(window);

            String title=driver.get().getTitle();
            if(title.equalsIgnoreCase(windowName)){
                break;
            }
        }
    }

     // Capture a screenshot
    public static String captureScreenshot(String testName) {
        String SCREENSHOT_DIR = "/target/testScreenshotsFiles/";
        File screenshot = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);  // Capture screenshot
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = System.getProperty("user.dir") + SCREENSHOT_DIR + testName + "_screenshot_" + timestamp + ".PNG";

        try {
            FileUtils.copyFile(screenshot, new File(filePath));  // Save screenshot
        } catch (Exception e) {
            logger.error("Error while capturing screenshot", e);
        }
        return filePath;
    }
    public static void brokenLinkVerification(By locator){
        wait=new WebDriverWait(driver.get(),Duration.ofSeconds(10L));
        List<WebElement> links=driver.get().findElements(locator);
//        for(int i=0;i<links.size();i++){
//            WebElement link=links.get(i);

        for (WebElement link : links) {
            String url = link.getDomAttribute("href");
            verifyLink(url);
        }
    }
    public static void verifyLink(String linkURL){
        try {
            URL url = new URL(linkURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.connect();

            int responseCode=httpURLConnection.getResponseCode();
            if(responseCode>400){
                System.out.println(linkURL+"-"+httpURLConnection.getResponseCode()+" is a broken link");
            }else{
                System.out.println(linkURL+"-"+httpURLConnection.getResponseCode());
            }
        }catch (Exception ignored){
        }
    }

    public static void autoOTPGenerator(String OTP){
        Base32 base32=new Base32();
        byte[] bytes=base32.decode(OTP);
        SecretKey key=new SecretKeySpec(bytes,"HmacSHA1");
        //String.format("%06d",autoOTPGenerator(key, Instant.now()));
    }
    public static List<Map<String, String>> readExcelData() throws IOException {
        String path = "//src//main//java//core//testdata//Excel_Imports//Credentials.xlsx";
        String sheetName = "sheet1";

        File src = new File(System.getProperty("user.dir") + path);

        List<Map<String, String>> credentials = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(src);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
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

    // Quit the browser and clean up
    public void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();  // Clean up the WebDriver from ThreadLocal
        }
    }
}
