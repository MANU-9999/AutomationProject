package com.ui;

import core.pageobjects.ElementsPage;
import core.testdata.ElementsDataProvider;
import core.testutils.ListenerUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerUtility.class)
public class ElementsTest extends TestBase {
    private ElementsPage elementsPage;
    @BeforeMethod(description = "Loading the test base")
    public void setup() {
        elementsPage = new ElementsPage();
    }

    @Test(dataProvider = "textBox", dataProviderClass = ElementsDataProvider.class)
    public void texBox(String FullName, String Email, String Address, String password) {
        elementsPage.textBox(FullName, Email, Address, password);
    }
    @Test
    public void checkBox() {
        elementsPage.checkBox();
    }

    @Test
    public void radioButtons() {
        elementsPage.radioButtons();
    }

    @Test
    public void buttons() {
        elementsPage.buttons();
    }

    @Test
    public void links() {
        elementsPage.links();
    }

    @Test
    public void brokenLinks() {
        elementsPage.brokenLinks();
    }

    @Test(description = "Test for uploading and downloading files")
    public void uploadAndDownload() {
        elementsPage.uploadAndDownload("D:\\Manoj\\Selenium\\AIS\\src\\main\\java\\core\\testdata\\alertsTest_1749812062159.png");
    }
}
