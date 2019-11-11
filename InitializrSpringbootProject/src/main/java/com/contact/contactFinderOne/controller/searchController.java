package com.contact.contactFinderOne.controller;

import com.contact.contactFinderOne.entities.Recherche;
import com.contact.contactFinderOne.entities.TableContact;
import com.contact.contactFinderOne.fct.ipAdresse;
import com.contact.contactFinderOne.repository.tableContactRepository;
import com.contact.contactFinderOne.security.UserDaoRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.contact.contactFinderOne.finders.myContactsBean;
import com.contact.contactFinderOne.finders.webFinderFunction;
import com.contact.contactFinderOne.repository.rechercheRepository;
import java.util.Date;

/**
 *
 * @author Amine
 */

@CrossOrigin
@RestController
@Validated
public class searchController {
    
    
    @Autowired
    private UserDaoRepository udr; 
    
    @Autowired
    private tableContactRepository tcr;
    
    @Autowired
    private rechercheRepository rr;
    
    
    

    
    @RequestMapping("/search/{id}/{ip}/{req}")
    public List<TableContact> getSearchResult(@PathVariable String id,@PathVariable String ip,@PathVariable String req) throws IOException {		

       System.out.println("test Search");
       
        ipAdresse ipa = new ipAdresse();
        
        Recherche r = new Recherche();
        
            r.setDates(new Date());
            r.setIdUsr(udr.findById(id));
            r.setRequete(req);
            r.setLocalisation(ipa.getZoneFormIpAddress(ip));
        
        rr.save(r);
        
        
        List<TableContact> lstRech = tcr.findByRequest(req);
        
        // just to test
        String tit="";
        for(TableContact rch: lstRech){
            tit = rch.getTitre();
            if(tit.length()>40)
                rch.setTitre(tit.substring(0, 40)+"...");
            
            if(rch.getMail().length()>30){
                rch.setMail(rch.getMail().substring(0, 30)+"\n"+rch.getMail().substring(30, rch.getMail().length()));
            }
            
              if(rch.getParagraph().length()>2){
                    String parg = rch.getParagraph();
                    if(parg.contains("</p> <p>")){
                        parg = parg.replaceAll("</p> <p>", "...");
                        rch.setParagraph(parg);
                    }
                        parg = parg.replaceAll("<p>", "");
                        parg = parg.replaceAll("</p>", "");
                        parg = parg.replaceAll("<strong>", "");
                        parg = parg.replaceAll("</strong>", "");
                        if(parg.length()>300){
                            parg = parg.substring(0, 300)+"...";
                        }
                        rch.setParagraph(parg);
                }else{
                        rch.setParagraph(rch.getTitre());
                    }
              
            System.out.println("mail: "+rch.getMail());
        }
 
        
       return lstRech;
    }
    
    
    
    
    
    @RequestMapping("/remplissage/{requete}")
    public List<TableContact> putInDataBaseWithRequest(@PathVariable String requete) throws IOException {		
          
       System.out.println("test remplissage");
       new Thread(new Runnable() {
              @Override
              public void run() {
                  try {        
                      remplirPar(requete.toLowerCase(), 4, 0);
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
              }
          }).start();          
          List<TableContact> lstTc = new ArrayList<TableContact>();
          return lstTc;
	}
    
    
    
    
   
    
    
    
    
//-----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------- 
//-----------------------------Methode de remplissage--------------------------------------
//-----------------------------------------------------------------------------------------    
    
public void remplirPar(String Question, int profondeur, int searchType) throws IOException{
    
    
webFinderFunction wff = new webFinderFunction();
        
List<myContactsBean> MML = new ArrayList<myContactsBean>();    
    
List<String> lstBing = new ArrayList<>();
wff.addListToList(lstBing, wff.listOfAllRequestedUrls(Question, profondeur) );

List<String> lstLink1 = new ArrayList<>();
List<String> lstLink2Contact = new ArrayList<>();

List<String> lstToRequestAllLinks = new ArrayList<>();
List<String> lstMail = new ArrayList<>();

//----- bing level ------
lstBing = wff.MyListFiltring(lstBing);

if(lstBing != null){
    for(String bing: lstBing){
        try {
            System.out.println("bing: "+bing);
            wff.addListToList(lstLink1, wff.urlsGetterFromUrl(bing)); 
        } catch (Exception e) {
            System.out.println("bing level: "+e);
        }
    }
    
  //----- lstLink1 level ------  
     lstLink1 = wff.MyListFiltring(lstLink1);

        if(lstLink1 != null){
            
            for(String link1: lstLink1)
                        if(link1.contains("ontac") || link1.contains("ONTAC"))
                           try {
                                System.out.println("link1: "+link1);
                                wff.addListToList(lstLink2Contact, wff.urlsGetterFromUrl(link1)); 
                            } catch (Exception e) {
                                System.out.println("link 1 level: "+e);
                            } 
                        

        }
        
  //----- lstToRequestAllLinks level ----      
    wff.addListToList(lstToRequestAllLinks, lstBing);
   // addListToList(lstToRequestAllLinks, lstLink1);
    wff.addListToList(lstToRequestAllLinks, lstLink2Contact);
    
     lstToRequestAllLinks = wff.MyListFiltring(lstToRequestAllLinks);
     
     if(lstToRequestAllLinks != null){
         List<TableContact> lstTc = new ArrayList<TableContact>();
         TableContact tc = new TableContact();
         for(String globalLink: lstToRequestAllLinks){
              System.out.println("globalLink: "+globalLink);
             
             if(searchType == 0){
                 for(String mail :  wff.mailsGetterFromUrl(globalLink)){
                            lstMail.add(mail);
                            
                            tc = new TableContact();
                            tc.setMail(mail);
                            tc.setPresentation(" ");
                            tc.setTags(Question);
                            tc.setTitre(wff.titreUrl(globalLink).toLowerCase());
                            tc.setUrl(globalLink);
                            tc.setUrlImg(" ");
                               
                            System.out.println("tc: "+mail);
                            tcr.save(tc);
                 }
             }
         }
     }
}


}
    
    
}
