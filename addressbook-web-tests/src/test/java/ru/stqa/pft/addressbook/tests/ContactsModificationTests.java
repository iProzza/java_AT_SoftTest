package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.Set;

public class ContactsModificationTests extends TestBase{
  @BeforeMethod public void ensurePreconditions(){
    app.contact().goToHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Volodya").withLastname("Volopas").withGroup("test1"), true);
    }
  }

  @Test()
  public void testContactsModification(){
    app.contact().goToHome();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("name").withLastname("lastname").withGroup("test1");
    app.contact().modify(contact);
    app.contact().goToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
