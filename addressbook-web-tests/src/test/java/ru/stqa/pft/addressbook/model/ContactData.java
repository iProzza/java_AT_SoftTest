package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
  @Id
  @Column(name = "Id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private  String firstname;

  @Expose
  @Column(name = "lastname")
  private  String lastname;

  @Expose
  @Column(name = "address")
  private String address;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Expose
  @Column(name = "home")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  private String workPhone;

  @Expose
  @Column(name = "email")
  private String email1;

  @Expose
  @Column(name = "email2")
  private String email2;

  @Expose
  @Column(name = "email3")
  private String email3;

  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @Transient
  private String group;


  @Column(name = "photo")
  private String photo;

  public File getPhoto() {
    if (photo != null) {
      return new File(photo);
    } return null;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastName(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }


  public int getId() {
    return id;
  }

  public String getFirstname() {return firstname;}

  public String getLastname() {return lastname;}

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }


  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return getId() == that.getId() && Objects.equals(getFirstname(), that.getFirstname()) && Objects.equals(getLastname(), that.getLastname()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getHomePhone(), that.getHomePhone()) && Objects.equals(getMobilePhone(), that.getMobilePhone()) && Objects.equals(getWorkPhone(), that.getWorkPhone()) && Objects.equals(getEmail1(), that.getEmail1()) && Objects.equals(getEmail2(), that.getEmail2()) && Objects.equals(getEmail3(), that.getEmail3()) && Objects.equals(getAllPhones(), that.getAllPhones()) && Objects.equals(getAllEmails(), that.getAllEmails()) && Objects.equals(getGroup(), that.getGroup()) && Objects.equals(getPhoto(), that.getPhoto());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFirstname(), getLastname(), getAddress(), getHomePhone(), getMobilePhone(), getWorkPhone(), getEmail1(), getEmail2(), getEmail3(), getAllPhones(), getAllEmails(), getGroup(), getPhoto());
  }
}

