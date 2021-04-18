package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wb;

    public void init() {
        wb = new FirefoxDriver();
        wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wb.get("http://localhost/addressbook");
        login("admin", "secret");
    }

    public void login(String username, String password) {
        wb.findElement(By.name("user")).clear();
        wb.findElement(By.name("user")).sendKeys(username);
        wb.findElement(By.name("pass")).clear();
        wb.findElement(By.name("pass")).sendKeys(password);
        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void returnToGroupPage() {
        wb.findElement(By.linkText("group page")).click();
    }

    public void submitGroupCreation() {
        wb.findElement(By.name("submit")).click();
    }

    public void fillGroupForm(GroupData groupData) {
        wb.findElement(By.name("group_name")).click();
        wb.findElement(By.name("group_name")).clear();
        wb.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wb.findElement(By.name("group_header")).click();
        wb.findElement(By.name("group_header")).clear();
        wb.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wb.findElement(By.name("group_footer")).click();
        wb.findElement(By.name("group_footer")).clear();
        wb.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    public void initGroupCreation() {
        wb.findElement(By.name("new")).click();
    }

    public void gotoGroupPage() {
        wb.findElement(By.linkText("groups")).click();
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

    public boolean isAlertPresent() {
        try {
            wb.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void deleteSelectedGroups() {
        wb.findElement(By.name("delete")).click();
    }

    //Используется как для групп, так и для контактов
    public void selectGroup() {
        wb.findElement(By.name("selected[]")).click();
    }

    public void returnToHome() {
        wb.findElement(By.linkText("home")).click();
    }

    public void submitContactCreation() {
        wb.findElement(By.name("submit")).click();
    }

    public void fillContactForm(ContactData contactData) {
        wb.findElement(By.name("firstname")).clear();
        wb.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wb.findElement(By.name("lastname")).clear();
        wb.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wb.findElement(By.name("nickname")).clear();
        wb.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wb.findElement(By.name("company")).clear();
        wb.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wb.findElement(By.name("address")).clear();
        wb.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wb.findElement(By.name("mobile")).clear();
        wb.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
        wb.findElement(By.name("email")).clear();
        wb.findElement(By.name("email")).sendKeys(contactData.getEmail());
    }

    public void initContactCreation() {
        wb.findElement(By.linkText("add new")).click();
    }

    public void popupHandling() {
        wb.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        wb.findElement(By.xpath("//input[@value='Delete']")).click();
    }
}
