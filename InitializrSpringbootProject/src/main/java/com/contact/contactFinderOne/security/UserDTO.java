package com.contact.contactFinderOne.security;

import java.util.Date;

public class UserDTO {
	private String id;
	private String password;
        private String activite;
        private Date dateInsc;
        private String adresseIp;
        private String CodeActivation;

        
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

        public String getAdresseIp() {
            return adresseIp;
        }

        public void setAdresseIp(String adresseIp) {
            this.adresseIp = adresseIp;
        }

        public String getCodeActivation() {
            return CodeActivation;
        }

        public void setCodeActivation(String CodeActivation) {
            this.CodeActivation = CodeActivation;
        }

        

  
        
}