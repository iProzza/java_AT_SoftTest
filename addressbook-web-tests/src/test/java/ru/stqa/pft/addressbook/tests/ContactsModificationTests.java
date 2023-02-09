package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsModificationTests extends TestBase{

  @Test
  public void testContactsModification(){
    app.getContactHelper().goToHome();
    app.getContactHelper().initContactsModification();
    app.getContactHelper().fillContactForm(new ContactData("Volodya", "Volopas", "404040", "volopas@volo.ru", null), false);
    app.getContactHelper().submitContactsModification();
    app.getNavigationHelper().goToHomePage();
  }
}
