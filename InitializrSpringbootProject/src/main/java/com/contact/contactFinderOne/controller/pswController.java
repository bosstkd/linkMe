/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.controller;

import com.contact.contactFinderOne.entities.DAOUser;
import com.contact.contactFinderOne.fct.Email;
import com.contact.contactFinderOne.fct.codification;
import com.contact.contactFinderOne.security.JwtUserDetailsService;
import com.contact.contactFinderOne.security.UserDTO;
import com.contact.contactFinderOne.security.UserDaoRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Amine
 */

@CrossOrigin
@RestController
public class pswController {
    
    @Autowired
    private PasswordEncoder bcryptEncoder;
    
    
    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    
    @Autowired
    private UserDaoRepository udr;
    
    @RequestMapping(value = "/activation/mail/", method = RequestMethod.POST)
	public ResponseEntity<?> psw(@RequestBody UserDTO user) throws Exception {

                DAOUser usr = udr.findById(user.getId());
                
                user.setActivite(usr.getActivite());
                user.setDateInsc(usr.getDateInsc());
                user.setCodeActivation(usr.getCodeConfirmation());
                
                String psw = new codification().psw();
                
                user.setPassword(psw);
                if(usr == null) return ResponseEntity.ok(new String("Ce compte n'existe pas."));
               return ResponseEntity.ok(userDetailsService.updatePsw(user, 0));
	}
        
        
    @RequestMapping(value = "/password", method = RequestMethod.POST)    
        public ResponseEntity<?> pswModif(@RequestBody myRequest req) throws IOException{
                        
              DAOUser usr = udr.findById(req.getId());
              String psw = usr.getPassword();
              
             if(!bcryptEncoder.matches(req.getAnsPsw(), psw)) return ResponseEntity.ok(new String("Veuillez vérifier l'ancien mot de passe svp !!"));
             
             codification cod = new codification();
             if(!cod.isGoodPassword(req.getNvPsw())) return ResponseEntity.ok(new String("Structure mot de passe trop faible."));
             
              UserDTO user = new UserDTO();
                user.setId(usr.getId());
                user.setActivite(usr.getActivite());
                user.setDateInsc(usr.getDateInsc());
                user.setCodeActivation(usr.getCodeConfirmation());
                
                user.setPassword(req.getNvPsw());
                
                if(usr == null) return ResponseEntity.ok(new String("Ce compte n'existe pas."));
               return ResponseEntity.ok(userDetailsService.updatePsw(user, 1));
        }
    
        
}