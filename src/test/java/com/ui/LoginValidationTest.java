package com.ui;

import core.testdata.TestDataProvider;
import core.testutils.ListenerUtility;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerUtility.class)
public class
LoginValidationTest extends TestBase {
    @Test(dataProvider = "ValidLogin", dataProviderClass = TestDataProvider.class)
    public void validLoginTest(String username, String password) {
        loginPage.validLogin(username, password);
    }
    @Test(dataProvider = "InValidLogin", dataProviderClass = TestDataProvider.class)
    public void inValidLoginTest(String userName, String pswd) {
        loginPage.invalidLogin(userName, pswd);
    }

}
