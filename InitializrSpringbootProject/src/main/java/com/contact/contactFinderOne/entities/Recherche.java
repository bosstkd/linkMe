/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "recherche")
public class Recherche implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "requete", nullable = false, length = 500)
    private String requete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dates", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dates;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "localisation", nullable = false, length = 255)
    private String localisation;
    @JoinColumn(name = "id_usr", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DAOUser idUsr;
    

    public Recherche() {
    }

    public Recherche(Long id) {
        this.id = id;
    }

    public Recherche(Long id, String requete, Date dates, String localisation) {
        this.id = id;
        this.requete = requete;
        this.dates = dates;
        this.localisation = localisation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequete() {
        return requete;
    }

    public void setRequete(String requete) {
        this.requete = requete;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public DAOUser getIdUsr() {
        return idUsr;
    }

    public void setIdUsr(DAOUser idUsr) {
        this.idUsr = idUsr;
    }



    @Override
    public String toString() {
        return "com.contact.contactFinderOne.entities.Recherche[ id=" + id + " ]";
    }
    
}
