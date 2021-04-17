package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        initContactCreation();
        fillContactForm(new ContactData("Nikolay", "Ruslyakov", "kolya", "Alfa-bank", "Ekaterinburg", "89123065091", "kolya.ruslyakov@mail.ru"));
        submitContactCreation();
        returnToHome();
    }


}
