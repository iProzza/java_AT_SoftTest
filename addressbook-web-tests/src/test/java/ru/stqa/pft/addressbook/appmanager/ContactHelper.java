package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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

  public void initContactsModificationById(int id) {
    wd.findElement(By.xpath(String.format("//input[@id='%s']/../..//img[@title='Edit']", id))).click();
  }

  public void submitContactsModification() {
    click(By.xpath("//input[22]"));
  }

  public void goToHome() {
    click(By.linkText("home"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectContact() {
    click(By.xpath("//div[2]/input"));
  }

  public void confirmDeletedContact() {
    wd.switchTo().alert().accept();
  }


  public void create(ContactData contact, boolean creation) {
    goToContactPage();
    fillContactForm(contact, creation);
    submitContactsForm();
    contactCache = null;
    goToStartPage();
  }

  public void modify(ContactData contact) {
    initContactsModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactsModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectContact();
    contactCache = null;
    confirmDeletedContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public Contacts contactCache = null;


  public Contacts all() {
    if (contactCache != null){
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr[@name='entry']"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastName = cells.get(1).getText();
      String firstName = cells.get(2).getText();
//      String address = cells.get(3).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(firstName).withLastname(lastName).withGroup(null);
      contactCache.add(contact);
    }
    return new Contacts(contactCache);
  }

}
