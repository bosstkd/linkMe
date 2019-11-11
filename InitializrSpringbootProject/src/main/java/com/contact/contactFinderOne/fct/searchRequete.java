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
public class searchRequete {
    
    
    public static void main(String args[]){
        searchRequete sq = new searchRequete();
        List<String> lstAtt = new ArrayList<>();
        lstAtt.add("url");
        lstAtt.add("mail");
        lstAtt.add("tags");
        str st = new str();
        List<String> lstWords = st.searchWordList("liste des clubs de taekwondo non pas a paris ile de france");
        System.out.println(sq.requeteToUseFirstPart("test", lstAtt, lstWords));
    }
    
    
    public String requeteToUseThirdPart(String tableName, List<String> listAttribute, List<String> listWordsToFind){
        
        String req = "select t from "+tableName+" t where ";
        
        String possibility = " ";
        
        for(String attribute:listAttribute){
            for(String words:listWordsToFind){
                possibility = possibility+"t."+attribute+" like :"+words+" or ";
            }
        }
        possibility = possibility.substring(0, possibility.length()-4);
        return req+possibility;
    }
    
    public String requeteToUseFirstPart(String tableName, List<String> listAttribute, List<String> listWordsToFind){
        String req = "select t from "+tableName+" t where ";
         String possibility = " ";
         boolean notLike = false;
         boolean andOk   = false;

         for(String attribute:listAttribute){
            for(String words:listWordsToFind){
                
                 if(changeByAND(words)) {
                     if(possibility.length()>5)
                        possibility = possibility.substring(0, possibility.length()-5)+" and "; 
                 }else if(changeByOR(words)){
                     if(possibility.length()>5)
                        possibility = possibility.substring(0, possibility.length()-5)+"  or "; 
                 }
                 else
                 if(words.equals("non")||words.equals("no")||words.equals("without")||words.equals("pas")) 
                     notLike = true;
                 else
                    if(notLike){
                        possibility = possibility+"t."+attribute+" not like :"+words+"  or ";
                        notLike = false;
                    }else{
                        possibility = possibility+"t."+attribute+" like :"+words+"  or ";
                    }
                
            }
        }
        possibility = possibility.substring(0, possibility.length()-5);
        return req+possibility;
    }
    
    public boolean changeByAND(String word){
        String[] tab = {"et", "alors", "avec", "comme", "plus", "pourtant", "meme", "des", "de", "du", "en","a"};
        for(String mot:tab)
            if(mot.equals(word)) return true;
        
        return false;
    }
    
     public boolean changeByOR(String word){
        String[] tab = {"dans", "lequel", "lieu", "pendant", "sinon", "soit", "ou"};
        for(String mot:tab)
            if(mot.equals(word)) return true;
        
        return false;
    }
    
    
}
