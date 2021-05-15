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
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("nickname"),contactData.getNickname());
        type(By.name("company"),contactData.getCompany());
        type(By.name("address"),contactData.getAddress());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());

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
    }

    public void initContactModification(int index) {
        wb.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public ContactData infoFromEditForm (ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wb.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wb.findElement(By.name("lastname")).getAttribute("value");
        String home = wb.findElement(By.name("home")).getAttribute("value");
        String mobile = wb.findElement(By.name("mobile")).getAttribute("value");
        String work = wb.findElement(By.name("work")).getAttribute("value");
        returnToHome();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobile(mobile).withWorkPhone(work);
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
        returnToHome();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        popupHandling();
        returnToHome();
    }

    public boolean isThereAContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wb.findElements(By.name("selected[]")).size();

    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wb.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (WebElement element:elements) {
            String LastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String FirstName = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(FirstName).withLastname(LastName));
        }
        return contacts;
    }


}
