package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ElementsPage extends BrowserActionsUtility {
    private static final By elementsTab = By.cssSelector("button.accordion-button");
    //    private static final By elementsTab= By.xpath("//*[name()='svg']//following::text()[contains(.,'Elements')]");
    private static final By textBox = By.xpath("//a[contains(@href,'text-box')]");
    private static final By fullName = By.id("fullName");
    private static final By email = By.id("email");
    private static final By address = By.id("address");
    private static final By password = By.id("password");
    private static final By submit = By.xpath("//input[@value='Submit']");
    //Check Box page elements
    private static final By CheckBoxTab = By.xpath("//a[contains(@href,'check-box.php')]");
    private static final By mainLevel = By.xpath("//span[contains(text(),'Main Level 1')]//preceding-sibling::span[@class='plus']");
    private static final By subLevel = By.xpath("//span[contains(text(),'Sub Level 1')]//preceding-sibling::span");
    private static final By lastLevel = By.xpath("//span[contains(text(),'Last Level 1')]//preceding-sibling::input[@id='c_io_1']");

    //
    private static final By radioButtonTab = By.xpath("//a[contains(@href,'radio-button.php')]");
    private static final By yes = By.xpath("//label[contains(text(),'Yes')]//preceding-sibling::input");
    private static final By impressive = By.xpath("//label[contains(text(),'Impressive')]//preceding-sibling::input");
    private static final By no = By.xpath("//label[contains(text(),'No')]//preceding-sibling::input");
    private static final By confirmationMSG = By.xpath("//div[contains(text(),'You have checked')]");
    //Buttons
    private static final By buttonTab = By.xpath("//a[contains(@href,'buttons.php')]");
    private static final By clickMe = By.xpath("//button[normalize-space()='Click Me']");
    private static final By rightClickMe = By.xpath("//button[normalize-space()='Right Click Me']");
    private static final By doubleClickMe = By.xpath("//button[normalize-space()='Double Click Me']");
    private static final By buttonClickMSG = By.cssSelector("div#welcomeDiv");

    //Links
    private static final By linksTab = By.xpath("//a[contains(@href,'links.php')]");
    private static final By home = By.xpath("//a[contains(text(),'Home')]");
    private static final By HomewPWPU = By.xpath("//a[contains(text(),'HomewPWPU')]");
    private static final By apiCall = By.xpath("//div/p/a");
    private static final By linkStatusWithMSG = By.xpath("//div[contains(text(),'Link has')][contains(@style,'display: block;')]");
    //broke links
    private static final By brokenLinksTab = By.xpath("//a[contains(@href,'broken-links.php')]");
    private static final By linksListInBrokenLinksScreen= By.xpath("//a");
 //upload and download links
    private static final By uploadAndDownloadTab = By.xpath("//a[contains(@href,'upload-download.php')]");
//    private static final By download = By.xpath("//a[contains(@href,'images/csharp_pdfcover.jpg')]");
    private static final By download= By.id("downloadButton");
    private static final By upload= By.id("uploadFile");


    public ElementsPage(WebDriver driver) {
        super(driver);
    }
    public static void navigateToElementsTab() {
        clickOn(elementsTab);
//        clickOn(textBox);
    }
    public static String alertMSG(By click, By textMSG) {
        String msg = null;
        try {
            clickOn(click);
            msg = getVisibleText(textMSG);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msg;
    }
    public static void textBox(String FullName, String Email, String Address, String Password) {
        navigateToElementsTab();
        enterText(fullName, FullName);
        enterText(email, Email);
        enterText(address, Address);
        enterText(password, Password);
        RegistrationPage.retryMethodForPages(submit);
    }
    public static void checkBox() {
        navigateToElementsTab();
        clickOn(CheckBoxTab);
        clickOn(mainLevel);
        clickOn(subLevel);
        clickOn(lastLevel);
    }
    public static void radioButtons() {
        navigateToElementsTab();
        clickOn(radioButtonTab);
        System.out.println(alertMSG(yes, confirmationMSG));
        System.out.println(alertMSG(impressive, confirmationMSG));
        if (!webElement(no).isEnabled()) {
            System.out.println("Element is disabled ");
        } else {
            System.out.println(alertMSG(impressive, confirmationMSG));
        }

    }
    public static void buttons() {
        try {
            navigateToElementsTab();
            clickOn(buttonTab);
            System.out.println(alertMSG(clickMe, buttonClickMSG));

            rightClick(rightClickMe);

            doubleClick(doubleClickMe);
            String s = getVisibleText(buttonClickMSG);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void links() {
        navigateToElementsTab();
        clickOn(linksTab);
        try {
            List<WebElement> links = getAttribute(apiCall);
            for (int i = 2; i < links.size(); i++) {
                clickOnWebElement(links.get(i));
                System.out.println(getVisibleText(linkStatusWithMSG));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(getVisibleText(linkStatusWithMSG));
        }
    }

    public static void brokenLinks(){
        navigateToElementsTab();
        clickOn(brokenLinksTab);
        brokenLinkVerification(linksListInBrokenLinksScreen);
    }
    public static void uploadAndDownload(String filePath)  {
        navigateToElementsTab();
        clickOn(uploadAndDownloadTab);
        clickOn(download);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }catch (AWTException ignored){

        }
        enterText(upload,filePath);
    }
}
