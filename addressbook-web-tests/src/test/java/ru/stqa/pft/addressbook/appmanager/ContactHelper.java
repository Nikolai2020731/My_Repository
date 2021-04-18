package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wb;

    public ContactHelper(WebDriver wb) {

        this.wb = wb;
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
