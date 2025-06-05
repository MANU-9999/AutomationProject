package core.testdata;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "ValidLogin")
    public Object[][] ValidLogin() {
        return new Object[][]{{"ManojKumarCH", "Password@123"}};
    }  @DataProvider(name = "InValidLogin")
    public Object[][] InValidLogin() {
        return new Object[][]{{"dummy_user", "dummy_password"}};
    }

    @DataProvider(name = "registrationForm")
    public Object[][] registrationForm(){
        return new Object[][]{{"TestUser","TestUser@gmail.com","987654321","2000-12-31","Mathematics","D://Manoj//1747680424251.jpeg","Hanuman Nagar","NCR","Agra"}};
    }
}
