package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddContactToGroup extends TestBase{
  ContactData contact;
  GroupData group;
  private boolean groupCreated;
  private boolean contactCreated;
  private String newGroupName = "GroupAdditionTest";

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().goToHome();
      app.contact().create(new ContactData().withFirstName("Добавленная группа").withLastName("Contact"), true);
      contact = app.db().contacts().iterator().next();
      contactCreated = true;
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(newGroupName));
      group = app.db().groups().iterator().next();
      groupCreated = true;
    }

    if (!(contactCreated && groupCreated)) {
      for (GroupData g : app.db().groups()) {
        for (ContactData c : app.db().contacts()) {
          if (!c.getGroups().contains(g)) {
            contact = c;
            group = g;
            return;
          }
        }
      }

      contact = app.db().contacts().iterator().next();
      group = new GroupData().withName(newGroupName + (int) (Math.random() * 1000));
      app.goTo().groupPage();
      app.group().create(group);
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contacts before = app.db().contacts();
    ContactData contactWithAddedGroup = contact.inGroup(group);

    app.contact().goToHome();
    app.contact().addToGroup(contact.getId(), group.getName());

    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(contact).withAdded(contactWithAddedGroup)));
  }
}
