package com.yart.user.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    private int id;

    @Enumerated
    private ContactType type;

    @Column
    private String value;

    public Contact(int id, String type, String value) {
        super();
        this.id = id;
        this.type = ContactType.getContactTypeByName(type);
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public enum ContactType {
        EMAIL("email"), PHONE("phone"), MOBILE("mobile"), ADDRESS("address"), SKYPE("im:skype"), UNDEFINED("");

        private String name;

        ContactType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public static ContactType getContactTypeByName(String name) {
            for (ContactType c : ContactType.values()) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
            return UNDEFINED;
        }

    }

}
