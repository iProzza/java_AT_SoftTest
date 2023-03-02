package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactsModificationTests extends TestBase{
  @BeforeMethod public void ensurePreconditions(){
    app.contact().goToHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("Volodya").withLastname("Volopas").withGroup("test1"), true);
    }
  }

  @Test()
  public void testContactsModification(){
    app.contact().goToHome();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("name").withLastname("lastname").withGroup("test1");
    app.contact().modify(contact);
    app.contact().goToHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() );
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
