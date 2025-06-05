package com.ui;

import core.pageobjects.LoginPage;
import core.pageobjects.ProfilePage;
import core.testdata.TestDataProvider;
import core.testutils.ListenerUtility;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerUtility.class)
public class
LoginValidationTest extends TestBase {
    @Test(dataProvider = "ValidLogin", dataProviderClass = TestDataProvider.class,
            enabled = false, groups = "smoke")
    public void ValidLoginTest(String userName, String pswd) {
        LoginPage.ValidLogin(userName, pswd);
    }
    @Test(dataProvider = "InValidLogin", dataProviderClass = TestDataProvider.class)
    public static void InValidLogintest(String userName, String pswd) {
        LoginPage.InValidLogin(userName, pswd);
    }

    @Test(dataProvider = "ValidLogin",dataProviderClass=TestDataProvider.class)
    public void URLBrokenTest(String userName,String pswd){
       // LoginPage.ValidLogin(userName,pswd);
        ProfilePage.pageURLDetails();
    }

}
