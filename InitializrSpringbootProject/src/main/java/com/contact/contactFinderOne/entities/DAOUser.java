/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "users")
public class DAOUser implements Serializable {

   // private static final long serialVersionUID = 1L;
    @Id
    private String id;
   
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    
    @Column(nullable = false)
    @JsonIgnore
    private String activite;
 
    @Column(nullable = false)
    @JsonIgnore
    private Date dateInsc;
  
    @Column
    @JsonIgnore
    private String adresseInsc;
    
    @Column(nullable = false)
    @JsonIgnore
    private String codeConfirmation;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    @JsonIgnore
    private Boolean admin;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    @JsonIgnore
    private Boolean activated;
  

    public DAOUser() {
    }

    public DAOUser(String id) {
        this.id = id;
    }

    public DAOUser(String id, String password, String activite, Date dateInsc, String adresseInsc) {
        this.id = id;
        this.password = password;
        this.activite = activite;
        this.dateInsc = dateInsc;
        this.adresseInsc = adresseInsc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public Date getDateInsc() {
        return dateInsc;
    }

    public void setDateInsc(Date dateInsc) {
        this.dateInsc = dateInsc;
    }

    public String getAdresseInsc() {
        return adresseInsc;
    }

    public void setAdresseInsc(String adresseInsc) {
        this.adresseInsc = adresseInsc;
    }

    public String getCodeConfirmation() {
        return codeConfirmation;
    }

    public void setCodeConfirmation(String codeConfirmation) {
        this.codeConfirmation = codeConfirmation;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

   

    
}
