package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

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
    app.getContactHelper().fillContactForm(new ContactData("Volodya", "Volopasss", null), false);
    app.getContactHelper().submitContactsModification();
    app.getNavigationHelper().goToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() );
  }
}
