package com.ui;

import core.constants.Browser;
import core.pageobjects.LoginPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {
     LoginPage loginPage;
    @Parameters({"browser", "env", "isHeadless"})
    @BeforeMethod(description = "Loading the test base")
    public void setup(@Optional("chrome") String browser,
                      @Optional("QA") String env,
                      @Optional("false") boolean isHeadless, ITestResult result) {
        loginPage = new LoginPage(Browser.valueOf(browser.toUpperCase()), isHeadless);
    }
    @AfterMethod()
    public void tearDown() {
        loginPage.quit();
    }
}
