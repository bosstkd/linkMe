/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.fct;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Amine
 */
public class test {
    
     public static void main(String args[]){
        codification cod = new codification();
           System.out.println(cod.isGoodPassword("123456"));
        for(int i = 0; i<10; i++){
            String psw = cod.psw();
            System.out.println(cod.isGoodPassword(psw)+" "+psw);
        }
        
    }
    
}
