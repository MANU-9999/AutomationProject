package com.ui;

import core.constants.Browser;
import core.env_config.Environment;
import core.testutils.BrowserActionsUtility;
import core.testutils.JSONUtility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
    protected WebDriver driver;
    protected String baseUrl;
    protected int maxAttempts;
    //Logger logger = LoggerUtility.getLogger(this.getClass());


    @Parameters({"browser", "QA", "isHeadless"})
    @BeforeMethod(description = "Loading the test base")
    public void setup(@Optional("chrome") String browser, @Optional("QA") String env,
                      @Optional("false") boolean isHeadless, ITestResult result) {
        Environment environment = JSONUtility.readEnvironmentConfig(env);
        if (environment != null) {
            baseUrl = environment.getUrl();
            maxAttempts = environment.getMax_attempts();
        }
        driver = new BrowserActionsUtility(Browser.valueOf(browser.toUpperCase()), isHeadless).driver();
        driver.get(baseUrl);
        driver.manage().window().maximize();

    }

    @AfterMethod()
    public void tearDown() {
        if (driver != null) {
            BrowserActionsUtility.quit();
        }
    }
}
