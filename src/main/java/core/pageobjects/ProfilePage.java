package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfilePage extends BrowserActionsUtility {
    private static final By LinkTags = By.tagName("a");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public static void pageURLDetails() {
        List<WebElement> linksElement = BrowserActionsUtility.getAttribute(LinkTags);
        Set<String> links = new HashSet<>();
        for (WebElement link : linksElement) {
            String url = link.getDomAttribute("href");
            links.add(url);
        }
        for (String link : links) {
            verifyLink(link);
        }
    }
    public static void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setConnectTimeout(500);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode >= 400) {
                System.out.println(url + ":" + connection.getResponseMessage()+"is broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " is broken");
        }

    }


}
