package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {
    protected WebDriver wb;

    public HelperBase(WebDriver wb) {
        this.wb = wb;
    }

    public void click(By locator) {
        wb.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text !=null) {
            wb.findElement(locator).clear();
            wb.findElement(locator).sendKeys(text);
        }
    }

    public void attach(By locator, File file) {
        if (file !=null) {
            wb.findElement(locator).sendKeys(file.getAbsolutePath());
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

    protected boolean isElementPresent(By locator) {
        try {
            wb.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
