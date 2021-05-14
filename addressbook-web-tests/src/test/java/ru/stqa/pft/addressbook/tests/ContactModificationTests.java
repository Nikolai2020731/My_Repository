package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrediction() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("kolya").withCompany("Alfa-bank")
                    .withAddress("Ekaterinburg").withMobile("89123065091").withEmail("kolya.ruslyakov@mail.ru").withGroup("test1"), true);
        }
    }

    @Test
    public void testContactModification () {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("KOLYA").withCompany("Alfa-bank")
                .withAddress("Ekaterinburg").withMobile("89123065091").withEmail("kolya.ruslyakov@mail.ru");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }



}
