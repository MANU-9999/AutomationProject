package com.ui;

import core.pageobjects.AlertsFramesAndWindowsPage;
import org.testng.annotations.Test;

public class AlertsFramesAndWindowsTest extends TestBase{


    @Test
    public void browserWindowsTest(){
        AlertsFramesAndWindowsPage.browserWindows("new-tab-sample");
    }
}
