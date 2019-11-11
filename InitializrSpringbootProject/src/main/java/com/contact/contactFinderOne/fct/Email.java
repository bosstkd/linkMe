/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.fct;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Amine
 */
  public class Email
{

  
   
   public boolean sendMail(String receiver, String sub, String text){
       
            String to = receiver;
            String subject = sub;
            String msg = text;
            final String from ="amitaek@gmail.com";
            final  String password ="j18M01a87";


            try {
                        Properties props = new Properties();  
                         props.setProperty("mail.transport.protocol", "smtp");     
                         props.setProperty("mail.host", "smtp.gmail.com");  
                         props.put("mail.smtp.auth", "true");  
                         props.put("mail.smtp.port", "465");  
                         props.put("mail.debug", "true");  
                         props.put("mail.smtp.socketFactory.port", "465");  
                         props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
                         props.put("mail.smtp.socketFactory.fallback", "false");  
                         Session session = Session.getDefaultInstance(props,  
                         new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {  
                            return new PasswordAuthentication(from,password);  
                        }  
                        });  

                        //session.setDebug(true);  
                        Transport transport = session.getTransport();  
                        InternetAddress addressFrom = new InternetAddress(from);  

                        MimeMessage message = new MimeMessage(session);  
                        message.setSender(addressFrom);  
                        message.setSubject(subject);  
                        message.setContent(msg, "text/plain");  
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  

                        transport.connect();  
                        Transport.send(message);  
                        transport.close();
       } catch (MessagingException e) {
                    e.printStackTrace();
                    return false;
       }
       
       return true;
   }
   
   public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
   
   
 }
