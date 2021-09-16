package ru.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.ContactData;
import ru.stqa.pft.mantis.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrediction() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("kolya").withCompany("Alfa-bank")
                    .withAddress("Ekaterinburg").withMobilePhone("89123065091").withEmail("kolya.ruslyakov@mail.ru").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification () {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("KOLYA").withCompany("Alfa-bank")
                .withAddress("Ekaterinburg").withMobilePhone("89123065091").withEmail("kolya.ruslyakov@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(),before.size());
        //assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }



}
