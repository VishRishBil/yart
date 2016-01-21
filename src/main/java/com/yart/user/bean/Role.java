package com.yart.user.bean;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

    @Id
    private int id;

    @Column
    private String roleName;

    @Column
    private String description;

    @Column
    private Locale locale;

    public Role(int id, String roleName, String description, String locale) {
        super();
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.locale = Locale.forLanguageTag(locale);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
