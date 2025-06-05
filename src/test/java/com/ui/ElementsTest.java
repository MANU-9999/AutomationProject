package com.ui;

import core.pageobjects.ElementsPage;
import core.testdata.ElementsDataProvider;
import org.testng.annotations.Test;

public class ElementsTest extends TestBase {
    // @Test(dataProvider="textBox",dataProviderClass= ElementsDataProvider.class)
    public void texBox(String FullName, String Email, String Address, String password) {
        ElementsPage.textBox(FullName, Email, Address, password);
    }

    //  @Test
    public void checkBox() {
        ElementsPage.checkBox();
    }

    //@Test
    public void radioButtons() {
        ElementsPage.radioButtons();
    }

    //    @Test
    public void buttons() {
        ElementsPage.buttons();
    }
   // @Test
    public void links() {
        ElementsPage.links();
    }
    //@Test
    public void brokenLinks() {
        ElementsPage.brokenLinks();
    }
    @Test(description = "Test for uploading and downloading files")
    public void uploadAndDownload() {
        ElementsPage.uploadAndDownload("D:\\Manoj\\1747680424251.jpeg");
    }
}
