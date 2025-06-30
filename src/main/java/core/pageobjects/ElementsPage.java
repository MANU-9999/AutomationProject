package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import core.testutils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ElementsPage extends BrowserActionsUtility {
    protected RegistrationPage registrationPage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private final By elementsTab = By.cssSelector("button.accordion-button");
    //    private  final By elementsTab= By.xpath("//*[name()='svg']//following::text()[contains(.,'Elements')]");
    private final By textBox = By.xpath("//a[contains(@href,'text-box')]");
    private final By fullName = By.cssSelector("input#fullname");
    private final By email = By.cssSelector("input#email");
    private final By address = By.xpath("//textarea[contains(@placeholder,'Currend Address')]");
    private final By password = By.id("password");
    private final By submit = By.xpath("//input[@value='Submit']");
    //Check Box page elements
    private final By CheckBoxTab = By.xpath("//a[contains(@href,'check-box.php')]");
    private final By mainLevel = By.xpath("//span[contains(text(),'Main Level 1')]//preceding-sibling::span[@class='plus']");
    private final By subLevel = By.xpath("//span[contains(text(),'Sub Level 1')]//preceding-sibling::span");
    private final By lastLevel = By.xpath("//span[contains(text(),'Last Level 1')]//preceding-sibling::input[@id='c_io_1']");

    //
    private final By radioButtonTab = By.xpath("//a[contains(@href,'radio-button.php')]");
    private final By yes = By.xpath("//label[contains(text(),'Yes')]//preceding-sibling::input");
    private final By impressive = By.xpath("//label[contains(text(),'Impressive')]//preceding-sibling::input");
    private final By no = By.xpath("//label[contains(text(),'No')]//preceding-sibling::input");
    private final By confirmationMSG = By.xpath("//div[contains(text(),'You have checked')]");
    //Buttons
    private final By buttonTab = By.xpath("//a[contains(@href,'buttons.php')]");
    private final By clickMe = By.xpath("//button[normalize-space()='Click Me']");
    private final By rightClickMe = By.xpath("//button[normalize-space()='Right Click Me']");
    private final By doubleClickMe = By.xpath("//button[normalize-space()='Double Click Me']");
    private final By buttonClickMSG = By.cssSelector("div#welcomeDiv");

    //Links
    private final By linksTab = By.xpath("//a[contains(@href,'links.php')]");
    private final By home = By.xpath("//a[contains(text(),'Home')]");
    private final By HomewPWPU = By.xpath("//a[contains(text(),'HomewPWPU')]");
    private final By apiCall = By.xpath("//div/p/a");
    private final By linkStatusWithMSG = By.xpath("//div[contains(text(),'Link has')][contains(@style,'display: block;')]");
    //broke links
    private final By brokenLinksTab = By.xpath("//a[contains(@href,'broken-links.php')]");
    private final By linksListInBrokenLinksScreen = By.xpath("//a");
    //upload and download links
    private final By uploadAndDownloadTab = By.xpath("//a[contains(@href,'upload-download.php')]");
    //    private  final By download = By.xpath("//a[contains(@href,'images/csharp_pdfcover.jpg')]");
    private final By download = By.id("downloadButton");
    private final By upload = By.id("uploadFile");

    public ElementsPage() {
        super();
    }

    public void navigateToElementsTab() {
        clickOn(elementsTab);
    }

    public String alertMSG(By click, By textMSG) {
        String msg = null;
        try {
            clickOn(click);
            msg = getVisibleText(textMSG);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return msg;
    }

    public void textBox(String FullName, String Email, String Address, String Password) {
        navigateToElementsTab();
        clickOn(textBox);
        enterText(fullName, FullName);
        enterText(email, Email);
        enterText(address, Address);
        enterText(password, Password);
        clickOn(submit);
    }

    public ElementsPage checkBox() {
        navigateToElementsTab();
        clickOn(CheckBoxTab);
        clickOn(mainLevel);
        clickOn(subLevel);
        clickOn(lastLevel);
        return this;
    }

    public ElementsPage radioButtons() {
        navigateToElementsTab();
        clickOn(radioButtonTab);
        logger.info(alertMSG(yes, confirmationMSG));
        logger.info(alertMSG(impressive, confirmationMSG));
        if (!webElement(no).isEnabled()) {
            logger.warn("Element is disabled");
        } else {
            logger.info(alertMSG(impressive, confirmationMSG));
        }
        return this;
    }

    public ElementsPage buttons() {
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
        return this;
    }

    public ElementsPage links() {
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
        return this;
    }

    public ElementsPage brokenLinks() {
        navigateToElementsTab();
        JSClick(brokenLinksTab);
        brokenLinkVerification(linksListInBrokenLinksScreen);
        return this;
    }

    public ElementsPage uploadAndDownload(String filePath) {
        navigateToElementsTab();
        clickOn(uploadAndDownloadTab);
        clickOn(download);
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException ignored) {
        }
        enterText(upload, filePath);
        return this;
    }
}
