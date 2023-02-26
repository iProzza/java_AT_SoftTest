package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactsModificationTests extends TestBase{
  @BeforeMethod public void ensurePreconditions(){
    app.contact().goToHome();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData("Volodya", "Volopas", "test1"), true);
    }
  }

  @Test()
  public void testContactsModification(){
    app.contact().goToHome();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(),"name", "lastname", "test1");
    app.contact().modify(index, contact);
    app.contact().goToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(index);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
