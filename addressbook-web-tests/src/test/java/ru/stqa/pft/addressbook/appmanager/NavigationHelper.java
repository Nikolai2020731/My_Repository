package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


    public NavigationHelper(WebDriver wb) {

        super(wb);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wb.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void addNewContactPage() {
        if (isElementPresent(By.id("content"))
                && isElementPresent(By.name("theform"))) {
            return;
        }
        click(By.linkText("add new"));
    }

}
