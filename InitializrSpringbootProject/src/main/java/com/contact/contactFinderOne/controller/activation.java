/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.controller;

import com.contact.contactFinderOne.entities.DAOUser;
import com.contact.contactFinderOne.security.UserDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Amine
 */
@Controller
public class activation {
    
    @Autowired
    private UserDaoRepository udr;
    
    @GetMapping("/activation/{code}")
    public RedirectView activate(@PathVariable String code){
        
        DAOUser usr = udr.findByCodeConfirmation(code);
        RedirectView redirectView = new RedirectView();

        
        if(usr.isActivated()){
            redirectView.setUrl("http://localhost:3000/activen");
        }else{
        usr.setActivated(true);
        udr.save(usr);
            redirectView.setUrl("http://localhost:3000/activer");
        }
        
        
        
        return redirectView;
    }
    
}
