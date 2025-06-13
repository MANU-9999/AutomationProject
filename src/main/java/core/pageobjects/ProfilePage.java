package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import core.testutils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProfilePage extends BrowserActionsUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static By LinkTags = By.tagName("a");

    public ProfilePage() {
        super();
    }

    public ProfilePage pageURLDetails() {
        List<WebElement> linksElement = getAttribute(LinkTags);
        Set<String> links = new HashSet<>();
        for (WebElement link : linksElement) {
            String url = link.getDomAttribute("href");
            links.add(url);
        }
        for (String link : links) {
            verifyLink(link);
        }
        return this;
    }
}
