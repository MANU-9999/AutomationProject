package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BrowserActionsUtility {
    private static final By mobileNo =By.xpath("//input[@id='username' and @type='text']");

    public SignUpPage(WebDriver driver) {
        super();
    }
    public SignUpPage doSignUpWith(String mobileNumber) {
        enterText(mobileNo, mobileNumber);
        return this;
    }
}
