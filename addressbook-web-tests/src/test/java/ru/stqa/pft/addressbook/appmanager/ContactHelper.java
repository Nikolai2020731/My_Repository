package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wb) {

        super(wb);
    }

    public void returnToHome() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("email"),contactData.getEmail());
        //attach(By.name("photo"),contactData.getPhoto());

        if (creation) {
            new Select(wb.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHome();
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {

        wb.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }

    public void popupHandling() {
        wb.switchTo().alert().accept();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wb.switchTo().alert().accept();
        click(By.cssSelector("div.msgbox"));
        //click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification(int id) {
        wb.findElement(By.xpath("//a[contains(@href, 'edit.php?id=" + id + "')]")).click();
        //wb.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public ContactData infoFromEditForm (ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wb.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wb.findElement(By.name("lastname")).getAttribute("value");
        String email = wb.findElement(By.name("email")).getAttribute("value");
        String email2 = wb.findElement(By.name("email2")).getAttribute("value");
        String email3 = wb.findElement(By.name("email3")).getAttribute("value");
        String home = wb.findElement(By.name("home")).getAttribute("value");
        String mobile = wb.findElement(By.name("mobile")).getAttribute("value");
        String work = wb.findElement(By.name("work")).getAttribute("value");
        String address = wb.findElement(By.name("address")).getAttribute("value");
        wb.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public void initContactModificationById(int id) {
        wb.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void create(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, b);
        submitContactCreation();
        contactCache = null;
        returnToHome();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        returnToHome();
    }

    public boolean isThereAContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wb.findElements(By.name("selected[]")).size();

    }

    public int count() {
        return wb.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wb.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = cells.get(1).getText();
            String firstname = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllEmails(allEmails).withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }


}
