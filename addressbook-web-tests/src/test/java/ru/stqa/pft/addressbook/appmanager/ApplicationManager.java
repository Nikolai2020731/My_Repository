package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final  Properties properties;
    WebDriver wb;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private String browser;
    private DBHelper dbHelper;

    public ApplicationManager(String browser) {

        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DBHelper();
        if (browser.equals(BrowserType.FIREFOX)) {
            wb = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wb = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wb = new InternetExplorerDriver();
        }


        wb.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wb.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(wb);
        navigationHelper = new NavigationHelper(wb);
        sessionHelper = new SessionHelper(wb);
        contactHelper = new ContactHelper(wb);
        sessionHelper.login(properties.getProperty("web.loginAdmin"), properties.getProperty("web.loginPassword"));


    }


    public void stop() {
        logOut();
        wb.quit();
    }

    public void logOut() {
        wb.findElement(By.linkText("Logout")).click();
    }

    public boolean isElementPresent(By by) {
        try {
            wb.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DBHelper db() {
        return dbHelper;
    }

}
