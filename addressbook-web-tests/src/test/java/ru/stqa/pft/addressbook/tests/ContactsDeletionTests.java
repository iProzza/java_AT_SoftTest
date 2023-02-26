package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactsDeletionTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    app.contact().goToHome();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Volodya").withLastname("Volopas").withGroup("test1"), true);
    }
  }

  @Test()
  public void testContactDeletion() {
    app.contact().goToHome();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.contact().goToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
      Assert.assertEquals(before, after);
  }

}
