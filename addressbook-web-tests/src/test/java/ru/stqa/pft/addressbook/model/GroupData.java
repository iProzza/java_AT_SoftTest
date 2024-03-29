package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  private  int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_name")
  private  String name;

  @Expose
  @Column(name = "group_header")
  private  String header;

  @Expose
  @Column(name = "group_footer")
  private  String footer;

  @ManyToMany(mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();

  public Set<ContactData> getContacts() {
    return contacts;
  }

  public int getId() {
    return id;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public String getName() {return name;}

  public String getHeader() {return header;}

  public String getFooter() {return footer;}


  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return getId() == groupData.getId() && Objects.equals(getName(), groupData.getName()) && Objects.equals(getHeader(), groupData.getHeader()) && Objects.equals(getFooter(), groupData.getFooter());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getHeader(), getFooter());
  }
}

