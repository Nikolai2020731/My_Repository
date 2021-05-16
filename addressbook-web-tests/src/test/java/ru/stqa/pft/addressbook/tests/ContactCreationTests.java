package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForCreationTests() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
        app.contact().returnToHome();
    }

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Nikolay").withLastname("Ruslyakov").withNickname("kolya").withCompany("Alfa-bank")
                .withAddress("Ekaterinburg").withMobilePhone("89123065091").withEmail("kolya.ruslyakov@mail.ru").withGroup("test1");
        app.contact().create(contact, true);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
    }

    @Test(enabled = false)
    public void testBadContactCreation() throws Exception {
        Contacts before = app.contact().all();
        app.goTo().addNewContactPage();
        ContactData contact = new ContactData()
                .withFirstname("Nikolay'").withLastname("Ruslyakov'").withMobilePhone("89123065091").withEmail("ivanovivan@yandex.ru").withGroup("test1");
        app.contact().create(contact, true);
        app.contact().returnToHome();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}
