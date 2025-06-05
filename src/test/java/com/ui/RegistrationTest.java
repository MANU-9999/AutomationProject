package com.ui;

import core.pageobjects.RegistrationPage;
import core.testdata.TestDataProvider;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {
    @Test(dataProvider = "registrationForm",dataProviderClass = TestDataProvider.class)
    public void registrationForm(String name,String email,String mobileNo,String dateOfBirth,String subject,String picturePath,String currentAddress,String stateName,String cityName) {
        RegistrationPage.registrationFormGrid(name,email,mobileNo,dateOfBirth,subject,picturePath,currentAddress,stateName,cityName);
    }

}
