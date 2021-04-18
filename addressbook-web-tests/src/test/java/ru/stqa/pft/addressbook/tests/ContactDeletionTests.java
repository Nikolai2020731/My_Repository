package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {

        app.getGroupHelper().selectGroup();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().popupHandling();
        app.getContactHelper().returnToHome();
    }


}
