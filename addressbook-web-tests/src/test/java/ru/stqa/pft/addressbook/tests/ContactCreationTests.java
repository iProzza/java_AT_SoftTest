package ru.stqa.pft.addressbook.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test()
  public void addsContactsTestsTest() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test1", null, null));
    }
    app.contact().goToHome();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Volodya", "MMM", "test1");
    app.contact().create(contact, true);
    app.contact().goToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}


