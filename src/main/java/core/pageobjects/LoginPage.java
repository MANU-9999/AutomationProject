package core.pageobjects;

import core.constants.Env;
import core.testutils.BrowserActionsUtility;
import core.testutils.JSONUtility;
import core.testutils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LoginPage extends BrowserActionsUtility {
    Logger logger = LoggerUtility.getLogger(BrowserActionsUtility.class);
    private static final By userName = By.id("email");
    private static final By password = By.id("password");
    private static final By login = By.cssSelector("input.btn.btn-primary");
    private static final By alertMessage = By.cssSelector("label.error");

    public LoginPage() {
        super();
        navigateToWebsite(JSONUtility.readEnvironmentConfig(Env.QA).getUrl());
    }

    public LoginPage validLogin(String username, String pswd) {
        enterText(userName, username);
        enterText(password, pswd);
        clickOn(login);
        return this;
    }

    public LoginPage invalidLogin(String username, String pswd) {
        enterText(userName, username);
        enterText(password, pswd);
        clickOn(login);
        List<WebElement> msg = getAttribute(alertMessage);
        if (msg != null) {
            logger.info("Filed has incorrect values, kindly recheck the details");
        } else {
            logger.info("Logged in successfully");
        }
        return this;
    }
}