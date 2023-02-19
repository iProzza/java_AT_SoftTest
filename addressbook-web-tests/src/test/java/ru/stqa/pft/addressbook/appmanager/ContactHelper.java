package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void goToContactPage() {
    click(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
//    type(By.name("home"), contactData.getPhone());
//    type(By.name("email"), contactData.getEmail());

    if (creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void submitContactsForm() {
    click(By.cssSelector("input:nth-child(87)"));
  }

  public void goToStartPage() {
    click(By.id("content"));
  }

  public void initContactsModification() {
    click(By.xpath("//td[8]/a/img"));

  }

  public void submitContactsModification() {
    click(By.xpath("//input[22]"));
  }

  public void goToHome() {
    click(By.linkText("home"));
  }


  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void deleteSelectContact() {
    click(By.xpath("//div[2]/input"));
  }

  public void confirmDeletedContact() {
    wd.switchTo().alert().accept();
  }


  public void createContact(ContactData contact,boolean creation) {
    goToContactPage();
    fillContactForm(contact, creation);
    submitContactsForm();
    goToStartPage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
