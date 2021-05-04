package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Nikolay", "Ruslyakov", "kolya", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("NIKOLAY", "RUSLYAKOV", "KOLYA", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHome();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before);
    }

}
