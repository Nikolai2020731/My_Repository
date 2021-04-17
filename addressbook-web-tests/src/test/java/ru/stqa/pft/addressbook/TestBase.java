package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver wb;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        wb = new FirefoxDriver();
        wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wb.get("http://localhost/addressbook");
        login("admin", "secret");
    }

    private void login(String username, String password) {
        wb.findElement(By.name("user")).clear();
        wb.findElement(By.name("user")).sendKeys(username);
        wb.findElement(By.name("pass")).clear();
        wb.findElement(By.name("pass")).sendKeys(password);
        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage() {
        wb.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
        wb.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
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

    protected void initGroupCreation() {
        wb.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
        wb.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
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

    protected void deleteSelectedGroups() {
        wb.findElement(By.name("delete")).click();
    }

    //Используется как для групп, так и для контактов
    protected void selectGroup() {
        wb.findElement(By.name("selected[]")).click();
    }

    protected void returnToHome() {
      wb.findElement(By.linkText("home")).click();
    }

    protected void submitContactCreation() {
      wb.findElement(By.name("submit")).click();
    }

    protected void fillContactForm(ContactData contactData) {
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

    protected void initContactCreation() {
      wb.findElement(By.linkText("add new")).click();
    }


    protected void popupHandling() {
      wb.switchTo().alert().accept();
    }

    protected void deleteSelectedContact() {
      wb.findElement(By.xpath("//input[@value='Delete']")).click();
    }
}
