package com.ui;

import core.pageobjects.LoginPage;
import core.testdata.TestDataProvider;
import core.testutils.ListenerUtility;
import org.testng.annotations.*;

@Listeners(ListenerUtility.class)
public class
LoginValidationTest extends TestBase {
    @Test(dataProvider = "ValidLogin", dataProviderClass = TestDataProvider.class, enabled = true, groups = "smoke")
    public void ValidLoginTest(String userName, String pswd) {
        LoginPage.ValidLogin(userName, pswd);
    }

    @Test(dataProvider = "InValidLogin", dataProviderClass = TestDataProvider.class, enabled = true, groups = "smoke")
    public void InValidLogin(String userName, String pswd) {
        LoginPage.InValidLogin(userName, pswd);
    }
}
