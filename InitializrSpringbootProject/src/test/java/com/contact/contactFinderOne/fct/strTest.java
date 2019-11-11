/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.fct;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Amine
 */
public class strTest {
    
    public strTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isName method, of class str.
     */
    @Test
    public void testIsName() {
        System.out.println("isName");
        String str = "Mahmoudi Mohammed El-Amine";
        str instance = new str();
        boolean expResult = true;
        boolean result = instance.isName(str);
        assertEquals(expResult, result);
      
    }

    /**
     * Test of searchForm method, of class str.
     */
    @Test
    public void testSearchForm() {
        System.out.println("searchForm");
        String recherche = "c'est un message";
        str instance = new str();
        String expResult = "c est un message ";
        String result = instance.searchForm(recherche);
        assertEquals(expResult, result);

    }

    /**
     * Test of searchWordList method, of class str.
     */
    @Test
    public void testSearchWordList() {
        System.out.println("searchWordList");
        String phrase = "je suis laba";
        str instance = new str();
        List<String> expResult = new ArrayList<>();
        expResult.add("suis");
        expResult.add("laba");
        List<String> result = instance.searchWordList(phrase);
        assertEquals(expResult, result);
      
    }

   
    /**
     * Test of doubleList method, of class str.
     */
    @Test
    public void testDoubleList() {
        System.out.println("doubleList");
        String phrase = "tata 12.5 nona 15.15 nono 20.05 adi";
        str instance = new str();
        List<Double> expResult = new ArrayList<>();
        expResult.add(12.5);
        expResult.add(15.15);
        expResult.add(20.05);
        List<Double> result = instance.doubleList(phrase);
        assertEquals(expResult, result);
     
    }

    /**
     * Test of intList method, of class str.
     */
    @Test
    public void testIntList() {
        System.out.println("intList");
        String phrase = "test 15 phrase 14 ok";
        str instance = new str();
        List<Integer> expResult = new ArrayList<>();
        expResult.add(15);
        expResult.add(14);
        
        List<Integer> result = instance.intList(phrase);
        assertEquals(expResult, result);
    
    }

    /**
     * Test of wordsList method, of class str.
     */
    @Test
    public void testWordsList() {
        System.out.println("wordsList");
        String phrase = "liste des mots voila";
        str instance = new str();
        List<String> expResult = new ArrayList<>();
        expResult.add("liste");
        expResult.add("des");
        expResult.add("mots");
        expResult.add("voila");
        List<String> result = instance.wordsList(phrase);
        assertEquals(expResult, result);
  
    }

    /**
     * Test of isMail method, of class str.
     */
    @Test
    public void testIsMail() {
        System.out.println("isMail");
        String str = "a-ek@hotmail.fr";
        str instance = new str();
        boolean expResult = true;
        boolean result = instance.isMail(str);
        assertEquals(expResult, result);
    }

    /**
     * Test of nameForm method, of class str.
     */
    @Test
    public void testNameForm() {
        System.out.println("nameForm");
        String str = "mahmoudi mohammed    el amine";
        str instance = new str();
        String expResult = "Mahmoudi Mohammed El Amine";
        String result = instance.nameForm(str);
        assertEquals(expResult, result);
    }

 

    /**
     * Test of getInt method, of class str.
     */
    @Test
    public void testGetInt() {
        System.out.println("getInt");
        String nb = "test 15 ok ok";
        str instance = new str();
        String expResult = "15";
        String result = instance.getInt(nb);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFloat method, of class str.
     */
    @Test
    public void testGetFloat() {
        System.out.println("getFloat");
        String nb = "test 15.75 ok ok";
        str instance = new str();
        String expResult = "15.75";
        String result = instance.getFloat(nb);
        assertEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class str.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        String str = "";
        str instance = new str();
        boolean expResult = true;
        boolean result = instance.isEmpty(str);
        assertEquals(expResult, result);
    }

    /**
     * Test of getWordBetween method, of class str.
     */
    @Test
    public void testGetWordBetween() {
        System.out.println("getWordBetween");
        String onStr = "<p>test word</p>";
        String str1 = "<p>";
        String str2 = "</p>";
        str instance = new str();
        String expResult = "test word";
        String result = instance.getWordBetween(onStr, str1, str2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getWordBetweenJustNextOne method, of class str.
     */
    @Test
    public void testGetWordBetweenJustNextOne() {
        System.out.println("getWordBetweenJustNextOne");
        String onStr = "<p>test word</p>nina</p>";
        String str1 = "<p>";
        String str2 = "</p>";
        str instance = new str();
        String expResult = "test word";
        String result = instance.getWordBetweenJustNextOne(onStr, str1, str2);
        assertEquals(expResult, result);
    }
    
}
