package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification(){
    app.getContactHelper().goToHome();
    int before = app.getContactHelper().getContactsCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Volodya", "Volopas", "test1"), true);
    }
    app.getContactHelper().goToHome();
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContactForm(new ContactData("Volodya", "Volopass", null), false);
    app.getContactHelper().submitContactsModification();
    app.getNavigationHelper().goToHomePage();
    int after = app.getContactHelper().getContactsCount();
    Assert.assertEquals(after, before );
  }
}
