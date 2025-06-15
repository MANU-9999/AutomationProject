package com.ui;

import core.constants.Browser;
import core.constants.Env;
import core.pageobjects.LoginPage;
import core.testutils.BrowserActionsUtility;
import core.testutils.EnvironmentManager;
import core.testutils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
    protected LoginPage loginPage;
    protected WebDriver driver;
    private static final Logger logger = LoggerUtility.getLogger(TestBase.class);

    @Parameters({"browser", "env", "isHeadless"})
    @BeforeMethod(description = "Loading the test base")
    public void setup(@Optional("chrome") String browser,
                      @Optional("QA") String env,
                      @Optional("false") boolean isHeadless, ITestResult result) {
        // Set environment
        EnvironmentManager.setEnvironment(Env.valueOf(env.toUpperCase()));

        // Initialize driver and page objects
        driver = BrowserActionsUtility.getDriver(Browser.valueOf(browser.toUpperCase()), isHeadless);
        loginPage = new LoginPage();

        logger.info("Test setup completed - Browser: {}, Environment: {}", browser, env);
    }

    @AfterMethod(description = "Tear Down the browser")
    public void tearDown() {
        BrowserActionsUtility.quitDriver();
        logger.info("Browser closed");
    }
}
