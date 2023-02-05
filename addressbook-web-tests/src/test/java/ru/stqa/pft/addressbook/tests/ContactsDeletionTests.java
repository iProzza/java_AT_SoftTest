package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactsDeletionTests extends TestBase{

  @Test
  public void tstContactDeletion(){
    app.getContactHelper().goToHome();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectContact();
    app.getContactHelper().confirmDeletedContact();
  }
}
