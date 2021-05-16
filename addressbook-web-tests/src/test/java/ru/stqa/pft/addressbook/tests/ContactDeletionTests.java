package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrediction() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("kolya").withCompany("Alfa-bank")
                    .withAddress("Ekaterinburg").withMobilePhone("89123065091").withEmail("kolya.ruslyakov@mail.ru").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertEquals(app.contact().count(),before.size()-1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }




}
