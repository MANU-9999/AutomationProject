package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
public class AlertsFramesAndWindowsPage extends BrowserActionsUtility {
    private static final By AlertsFramesWindowsTab = By.xpath("//button[@aria-controls='collapseThree']");
    private static final By browserWindows = By.xpath("//a[contains(@href,'browser-windows.php')]");
    private static final By newTab = By.xpath("//button[contains(text(),'New Tab')]");
    private static final By newTabText = By.xpath("(//div/h1)[2]");
    private static final By newWindow = By.xpath("//button[text()='New Window']");
    private static final By newWindowText = By.xpath("(//div/h1/following-sibling::text())[2]");
    private static final By newWindowMessage = By.xpath("//button[text()='New Window Message']");
    private static final By newWindowMessageText = By.xpath("(//div/h1/following-sibling::text())[2]");
    //Alerts
    private static final By AlertsTab = By.xpath("//a[contains(@href,'alerts.php')]");
    private static final By alertButton = By.xpath("//*[contains(text(),'Click Button to see alert')]/following-sibling::button[@type='button']");
    private static final By clickMeButton = By.xpath("//*[contains(text(),'alert will appear after 5 seconds')]/following-sibling::button[@type='button']");
    private static final By clickMeButton_confirmBox = By.xpath("//*[contains(text(),'On button click, confirm box will appear')]/following-sibling::button[@type='button']");
    private static final By clickMeButton_promptBox = By.xpath("//*[contains(text(),'On button click, prompt box will appear')]/following-sibling::button[@type='button']");
    //frames
    private static final By FramesTab = By.xpath("//a[@href='frames.php']");
    private static final By iframeValue1 = By.xpath("//iframe[1]");
    private static final By iframeValue2 = By.xpath("//iframe[2]");
    private static final By pageHeader = By.xpath("//div/h1");
    private static final By pageLogo = By.xpath("//*[name()='svg' and @class='logo-desktop']");
    private static final By searchBar = By.id("mobile-search-strings");
    private static final By iframe2Header = By.xpath("//h2[@class='mt-5']");


    //Nested Frames
    private static final By NestedFramesTab = By.xpath("//a[contains(@href,'frames.php')]");

    //Modal Alerts
    private static final By modalTab = By.xpath("//a[@href='modal-dialogs.php']");
    private static final By smallModalButton = By.xpath("//button[contains(text(),'Small Modal')]");
    private static final By smallModalBodyText = By.cssSelector("div#exampleModalSm div.modal-body");
    private static final By smallModalAlertClose = By.cssSelector("div#exampleModalSm button.btn.btn-primary");

    private static final By largeModalButton = By.xpath("//button[contains(text(),'Large Modal')]");
    private static final By largeModalBodyText = By.cssSelector("div#exampleModalLg div.modal-body");
    private static final By largeModalAlertClose = By.cssSelector("div#exampleModalLg button.btn.btn-primary");



    public AlertsFramesAndWindowsPage() {
        super();
    }
    public AlertsFramesAndWindowsPage navigateToAlertsFramesWindows() {
        clickOn(AlertsFramesWindowsTab);
        return this;
    }
    public AlertsFramesAndWindowsPage  browserWindows(String newTabName) {
        navigateToAlertsFramesWindows();
        clickOn(browserWindows);

        clickOn(newTab);
        getWindowHandles(newTabName);
        System.out.println(getVisibleText(newTabText));

        clickOn(newWindow);
        getWindowHandles(newTabName);
        System.out.println(getVisibleText(newTabText));


        clickOn(newWindowMessage);
        getWindowHandles(newTabName);
        System.out.println(getVisibleText(newTabText));
        return this;
    }
    public AlertsFramesAndWindowsPage alerts() {
        navigateToAlertsFramesWindows();
        clickOn(AlertsTab);
        clickOn(alertButton);
        handleAlert("accept",null);

        clickOn(clickMeButton);
        handleAlert("accept",null);

//      clickOn(clickMeButton_confirmBox);

        clickOn(clickMeButton_promptBox);
        handleAlert("Send", "Sample Text");
        return this;
    }

    public AlertsFramesAndWindowsPage frames(String searchText){
        navigateToAlertsFramesWindows();
        clickOn(FramesTab);

        iframeActivity("byElement",iframeValue1,false);
        System.out.println(getVisibleText(pageHeader));

        clickOn(pageLogo);
        enterText(searchBar,searchText);
        clearText(searchBar);

        defaultIframe();
        System.out.println(getVisibleText(iframe2Header));

        iframeActivity("byElement",iframeValue2,false);
        System.out.println(getVisibleText(pageHeader));
        return this;
    }
    public AlertsFramesAndWindowsPage modalAlerts(){
        navigateToAlertsFramesWindows();
        clickOn(modalTab);

        clickOn(smallModalButton);
        System.out.println(getVisibleText(smallModalBodyText));
        clickOn(smallModalAlertClose);


        clickOn(largeModalButton);
        System.out.println(getVisibleText(largeModalBodyText));
        clickOn(largeModalAlertClose);
        return this;
    }

}
