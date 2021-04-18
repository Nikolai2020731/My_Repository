package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
    private WebDriver wb;

    public SessionHelper(WebDriver wb) {

        this.wb = wb;
    }

    public void login(String username, String password) {
        wb.findElement(By.name("user")).clear();
        wb.findElement(By.name("user")).sendKeys(username);
        wb.findElement(By.name("pass")).clear();
        wb.findElement(By.name("pass")).sendKeys(password);
        wb.findElement(By.xpath("//input[@value='Login']")).click();
    }

}
