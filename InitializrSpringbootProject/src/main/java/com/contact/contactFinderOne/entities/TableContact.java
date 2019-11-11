/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "table_contact")
public class TableContact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "mail", nullable = false, length = 255)
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 600)
    @Column(name = "url", nullable = false, length = 600)
    private String url;

    @Size(min = 0, max = 600)
    @Column(name = "url_img", nullable = true, length = 600)
    private String urlImg;
    
    

    @Size(min = 0, max = 600)
    @Column(name = "tags", nullable = true, length = 600)
    private String tags;
    
 
    @Size(min = 0, max = 600)
    @Column(name = "presentation", nullable = true, length = 600)
    private String presentation;
    
 
    @Size(min = 0, max = 600)
    @Column(name = "titre", nullable = true, length = 600)
    private String titre;
    
    @Size(min = 0, max = 5000)
    @Column(name = "paragraph", nullable = true, length = 5000)
    private String paragraph;

    public TableContact() {
    }

    public TableContact(String mail) {
        this.mail = mail;
    }

    public TableContact(String mail, String url) {
        this.mail = mail;
        this.url = url;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
    
    

    
}
