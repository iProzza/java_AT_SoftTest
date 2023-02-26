package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactsDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.getContactHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Volodya", "Volopas", "test1"), true);
    }
  }

  @Test()
  public void testContactDeletion() {
    app.getContactHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectContact();
    app.getContactHelper().confirmDeletedContact();
    app.getContactHelper().goToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
      Assert.assertEquals(before, after);
  }
}
