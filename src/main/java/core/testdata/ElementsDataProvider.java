package core.testdata;

import org.testng.annotations.DataProvider;

public class ElementsDataProvider {
    @DataProvider(name = "textBox")
    public Object[][] textBox(){
        return new Object[][]{{"John","Johndoe@123domain.com","This a sample address for the page. ","Password@123"}};
    }
}
