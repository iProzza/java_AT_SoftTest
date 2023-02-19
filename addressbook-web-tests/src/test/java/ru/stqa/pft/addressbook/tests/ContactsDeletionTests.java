package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsDeletionTests extends TestBase{

  @Test
  public void tstContactDeletion(){
    app.getContactHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
        app.getContactHelper().createContact(new ContactData("Volodya", "HHHH", "test1"), true);
    }
    app.getContactHelper().goToHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectContact();
    app.getContactHelper().confirmDeletedContact();
  }
}
