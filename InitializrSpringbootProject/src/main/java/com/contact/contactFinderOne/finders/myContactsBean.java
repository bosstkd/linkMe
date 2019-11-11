/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.finders;


/**
 *
 * @author Amine
 */
public class myContactsBean {
    
       private String url;
       private String contactsLink;
       
       private String titre;
       private String presentation;
       private String tags;
       private String img;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContactsLink() {
        return contactsLink;
    }

    public void setContactsLink(String contactsLink) {
        this.contactsLink = contactsLink;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
    
    

    public myContactsBean(String url, String contactsLink, String titre, String tags, String description) {
        this.url = url;
        this.contactsLink = contactsLink;
        this.titre = titre;
        this.tags = tags;
        this.presentation = description;
    }
    
    

   
   
        
        
        
}
