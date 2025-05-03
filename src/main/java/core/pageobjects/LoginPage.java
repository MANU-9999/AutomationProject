package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BrowserActionsUtility {
    private static final By userName = By.id("userName");
    private static final By password = By.id("password");
    private static final By login = By.id("login");
    private static final By alertMessage = By.cssSelector("p#name");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public static void ValidLogin(String username,String pswd) {
        BrowserActionsUtility.enterText(userName, username);
        BrowserActionsUtility.enterText(password, pswd);
        BrowserActionsUtility.clickOn(login);
    }  public static void InValidLogin(String username,String pswd) {
        BrowserActionsUtility.enterText(userName, username);
        BrowserActionsUtility.enterText(password, pswd);
        BrowserActionsUtility.clickOn(login);
        String  s=BrowserActionsUtility.getVisibleText(alertMessage);
        Assert.assertEquals(s,"Invalid username or password!");
    }
}