package ru.stqa.pft.mantis.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import ru.stqa.pft.mantis.model.ContactData;
import ru.stqa.pft.mantis.model.Contacts;
import ru.stqa.pft.mantis.model.GroupData;
import ru.stqa.pft.mantis.model.Groups;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }

    }

    @BeforeMethod
    public void ensurePreconditionsForCreationTests() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        //app.contact().returnToHome();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.db().contacts();
        Groups groups = app.db().groups();
        //File photo = new File("src/test/resources/photo1.png");
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        //assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }


}
