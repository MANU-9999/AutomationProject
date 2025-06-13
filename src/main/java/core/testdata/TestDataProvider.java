package core.testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "ValidLogin")
    public Object[][] ValidLogin() {
        return new Object[][]{{"Johndoe@123domain.com", "Password@123"}};
    }  @DataProvider(name = "InValidLogin")
    public Object[][] InValidLogin() {
        return new Object[][]{{"dummy_user", "dummy_password"}};
    }

    @DataProvider(name = "registrationForm")
    public Object[][] registrationForm(){
        return new Object[][]{{"TestUser","TestUser@gmail.com","987654321","2000-12-31","Mathematics","D:\\Manoj\\Selenium\\DEMOQA\\src\\main\\java\\core\\testdata\\registrationForm_1749819287797.png","Hanuman Nagar","NCR","Agra"}};
    }
}
