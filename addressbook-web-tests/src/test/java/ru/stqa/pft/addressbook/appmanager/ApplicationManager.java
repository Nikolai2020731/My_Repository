package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wb;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wb = new FirefoxDriver();
        wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wb.get("http://localhost/addressbook");
        groupHelper = new GroupHelper(wb);
        navigationHelper = new NavigationHelper(wb);
        sessionHelper = new SessionHelper(wb);
        contactHelper = new ContactHelper(wb);
        sessionHelper.login("admin", "secret");
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

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
