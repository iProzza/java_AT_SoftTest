package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactsDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().goToHome();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Volodya").withLastName("Volopas").withGroup("test1"), true);
    }
  }

  @Test()
  public void testContactDeletion() {
    app.contact().goToHome();
    Contacts before = app.contact().all();
    ContactData deleteContact = before.iterator().next();
    app.contact().delete(deleteContact);
    app.contact().goToHome();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deleteContact)));
  }

}
