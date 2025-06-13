package com.ui;

import core.pageobjects.AlertsFramesAndWindowsPage;
import core.testutils.ListenerUtility;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerUtility.class)
public class AlertsFramesAndWindowsTest extends TestBase {
    private AlertsFramesAndWindowsPage afwPage;
    @BeforeMethod(description = "Loading the test base")
    public void setup() {
        afwPage = new AlertsFramesAndWindowsPage();
    }
    @Test
    public void browserWindowsTest() {
        afwPage.browserWindows("Web Tables");
    }
    @Test
    public void alertsTest() {
        afwPage.alerts();
    }
    @Test
    public void framesTest() {
        afwPage.frames("Java");
    }
    @Test
    public void modalAlertsTest() {
        afwPage.modalAlerts();
    }
}
