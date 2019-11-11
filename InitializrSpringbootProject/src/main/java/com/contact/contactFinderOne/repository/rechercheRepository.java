/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.repository;

import com.contact.contactFinderOne.entities.Recherche;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Amine
 */
public interface rechercheRepository extends JpaRepository<Recherche, Long>{
    
}
