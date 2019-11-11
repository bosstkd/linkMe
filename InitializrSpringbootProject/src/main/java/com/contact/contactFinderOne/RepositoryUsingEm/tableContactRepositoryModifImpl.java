/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.RepositoryUsingEm;

import com.contact.contactFinderOne.entities.TableContact;
import com.contact.contactFinderOne.fct.searchRequete;
import com.contact.contactFinderOne.fct.str;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author Amine
 */

public class tableContactRepositoryModifImpl implements tableContactRepositoryModif{

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<TableContact> findByRequest(String question){
         searchRequete sr = new searchRequete();
         
         List<String> listAttribute = new ArrayList<>();
         listAttribute.add("mail");
         listAttribute.add("paragraph");
         listAttribute.add("tags");
         listAttribute.add("titre");
         
         List<String> listWordsToFind = new str().searchWordList(question);
         
       String req = sr.requeteToUseFirstPart("TableContact", listAttribute, listWordsToFind);
       req = req+" order by t.presentation desc";
       System.out.println(req);
       Query q = entityManager.createQuery(req);
      // q.setParameter("wilaya", wilaya);
     
      
      List<String> listWordsToFind2 = new str().searchWordList2(question);
      
      for(String words:listWordsToFind2)
          q.setParameter(words, "%"+words+"%");
      
        return q.getResultList();
    }
 
}
