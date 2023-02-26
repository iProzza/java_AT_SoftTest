package ru.stqa.pft.addressbook.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void addsContactsTestsTest() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().crateGroup(new GroupData("test1", null, null));
    }
    app.getContactHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Volodya", "MMM", "test1");
    app.getContactHelper().createContact(contact, true);
    app.getContactHelper().goToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}


