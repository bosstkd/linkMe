/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.controller;

import com.contact.contactFinderOne.entities.TableContact;
import java.util.List;

/**
 *
 * @author Amine
 */
public class representalSearch {
    private int nbPage;    
    private List<TableContact> listTc;

    public List<TableContact> getListTc() {
        return listTc;
    }

    public void setListTc(List<TableContact> listTc) {
        this.listTc = listTc;
    }

    public int getNbPage() {
       return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }
    
    
    
    
}
