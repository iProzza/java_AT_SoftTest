package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification(){
    app.getContactHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Volodya", "Volopas", "test1"), true);
    }
    app.getContactHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactsModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"name", "lastname", "test1");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactsModification();
    app.getContactHelper().goToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
