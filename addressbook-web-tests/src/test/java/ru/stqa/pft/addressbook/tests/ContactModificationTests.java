package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification () {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Nikolay", "Ruslyakov", "kolya", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru", "test1"), true);
        }
        app.getGroupHelper().selectGroup();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("NIKOLAY", "RUSLYAKOV", "KOLYA", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHome();

    }

}
