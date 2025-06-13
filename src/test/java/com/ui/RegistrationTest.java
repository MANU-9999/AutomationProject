package com.ui;

import core.pageobjects.RegistrationPage;
import core.testdata.TestDataProvider;
import core.testutils.ListenerUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ListenerUtility.class)
public class RegistrationTest extends TestBase {

    private RegistrationPage registrationPage;
    @BeforeMethod
    public void setUp(){
        registrationPage=new RegistrationPage();
    }
    @Test(dataProvider = "registrationForm",dataProviderClass = TestDataProvider.class)
    public void registrationForm(String name,String email,String mobileNo,String dateOfBirth,String subject,String picturePath,String currentAddress,String stateName,String cityName) {
        registrationPage.registrationFormGrid(name,email,mobileNo,dateOfBirth,subject,picturePath,currentAddress,stateName,cityName);
    }

}
