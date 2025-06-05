package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsFramesAndWindowsPage extends BrowserActionsUtility {
        private static final By AlertsFramesWindowsTab = By.xpath("//button[@aria-controls='collapseThree']");
    private static final By browserWindows = By.xpath("//a[contains(@href,'browser-windows.php')]");
    private static final By newTab = By.xpath("//button[contains(text(),'New Tab')]");
    public AlertsFramesAndWindowsPage(WebDriver driver) {
        super(driver);
    }
    //

    public static void  navigateToAlertsFramesWindows() {
        clickOn(AlertsFramesWindowsTab);
    }
    public static void browserWindows(String windowName){
        ElementsPage.navigateToElementsTab();
        clickOn(browserWindows);
        clickOn(newTab);
        getWindowHandles(windowName);

        
    }
}
