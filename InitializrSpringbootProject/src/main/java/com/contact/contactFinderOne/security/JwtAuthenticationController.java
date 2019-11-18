package com.contact.contactFinderOne.security;

//import java.util.Objects;

import com.contact.contactFinderOne.entities.DAOUser;
import com.contact.contactFinderOne.fct.Email;
import com.contact.contactFinderOne.fct.codification;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
        
        @Autowired
        private UserDaoRepository udr;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

                DAOUser usr = udr.findById(authenticationRequest.getUsername());
                if(usr != null)
                if(!usr.isActivated()){
                    
                    JSONObject jsonObject = null;
                    
                    try {
                         jsonObject = new JSONObject("{'compte':'non activer'}");
                   }catch (JSONException err){
                        err.printStackTrace();
                   }
                    
                    return ResponseEntity.ok(jsonObject);
                }
            
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
                
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
                final String token = jwtTokenUtil.generateToken(userDetails);
		
                return ResponseEntity.ok(new JwtResponse(token));
                
	}
        
    
	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
                if(userDetailsService.userExist(user))
                    return ResponseEntity.ok(new String("Compte déjà existant."));
            
                user.setDateInsc(new Date());
                user.setCodeActivation(new codification().cd_structure(user.getId()));
                
                if(!new Email().isValidEmailAddress(user.getId()))
                    return ResponseEntity.ok(new String("Veuillez vérifier le format du mail svp"));
                
                if(!new codification().isGoodPassword(user.getPassword()))
                    return ResponseEntity.ok(new String("Structure mot de passe trop faible."));
                
                
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	private void authenticate(String id, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}