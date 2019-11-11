package com.contact.contactFinderOne.security;

import com.contact.contactFinderOne.entities.DAOUser;
import com.contact.contactFinderOne.fct.Email;
import com.contact.contactFinderOne.fct.ipAdresse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    
    
          
    
	
	@Autowired
	private UserDaoRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		DAOUser user = userDao.findById(id);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with id: " + id);
		}
		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),
			                                                      new ArrayList<>());
	}
        
	
	public DAOUser save(UserDTO user) throws IOException {
		DAOUser newUser = new DAOUser();
		
                newUser.setId(user.getId());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
                newUser.setActivite(user.getActivite());
                newUser.setDateInsc(user.getDateInsc());
                
                ipAdresse ip = new ipAdresse();
                newUser.setAdresseInsc(ip.getZoneFormIpAddress(user.getAdresseIp()));
                newUser.setCodeConfirmation(user.getCodeActivation());
                newUser.setActivated(false);
                newUser.setAdmin(false);
                
                Email em = new Email();
                
                
                 String text="Bonjour,\nContacteFinder vous souhaite la bienvenu merci de cliquer sur le lien au dessous pour activer votre compte:\n"
                           + "lien: http://localhost:8080/activation/"+user.getCodeActivation()+"\n\n"
                           + "Bien Cordialement,\nContact Finder.";  
                try {
                     new Thread(new Runnable() {
                         @Override
                         public void run() {
                                em.sendMail(user.getId(), "Activation compte ContactFinder", text);                         }
                             }).start();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                
                
                
            return userDao.save(newUser);
	}
        
        public boolean userExist(UserDTO user){
            return userDao.findById(user.getId())!= null;
        }
        
        
        
}