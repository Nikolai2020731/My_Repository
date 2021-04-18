package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.initContactCreation();
        app.fillContactForm(new ContactData("Nikolay", "Ruslyakov", "kolya", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru"));
        app.submitContactCreation();
        app.returnToHome();
    }


}
