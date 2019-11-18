package com.contact.contactFinderOne.controller;

import com.contact.contactFinderOne.entities.Recherche;
import com.contact.contactFinderOne.entities.TableContact;
import com.contact.contactFinderOne.fct.ipAdresse;
import com.contact.contactFinderOne.fct.str;
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
    

    
  //----------------------------------------------------------------------------  
    
    
    
    
    
    
     @RequestMapping("/search/{id}/{ip}/{req}/{page}")
    public representalSearch getSearchResult(@PathVariable String id,@PathVariable String ip,@PathVariable String req, @PathVariable int page) throws IOException {		

        ipAdresse ipa = new ipAdresse();
        
        Recherche r = new Recherche();
        
            r.setDates(new Date());
            r.setIdUsr(udr.findById(id));
            r.setRequete(req);
            r.setLocalisation(ipa.getZoneFormIpAddress(ip));
        
        rr.save(r);
        
        
        List<TableContact> lstRech = tcr.findByRequest(req);
        
        // just to test
        String tit;
        for(TableContact rch: lstRech){
            tit = rch.getTitre();
            tit = new str().asciiConvert(tit);
            if(tit.length()>40)
                rch.setTitre(tit.substring(0, 40)+"...");
            else
                rch.setTitre(tit);
            
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
                        parg = parg.replaceAll("<br", "");
                        parg = parg.replaceAll("/>", "");
                        parg = parg.replaceAll("<", "");
                        parg = parg.replaceAll(">", "");
                        parg = parg.replaceAll("/", " ");
                 
                        if(parg.length()>300){
                            parg = parg.substring(0, 300)+"...";
                        }
                        parg = new str().asciiConvert(parg);
                        rch.setParagraph(parg);
                }else{
                        rch.setParagraph(rch.getTitre());
                    }
              
           // System.out.println("mail: "+rch.getMail());
        }
 
       representalSearch rs = new representalSearch();
       
       int sp = 10;
       
       int nbpage = nbPage(lstRech, sp);
       
       List<TableContact>  laList = new ArrayList<>();
       
     
       int s = (page-1)*10+10;
       
       if(lstRech.size()>=s){
           for(int i = (page-1)*10; i<s; i++){
               laList.add(lstRech.get(i));
           }
       }else{
           for(int i = (page-1)*10; i<lstRech.size(); i++){
               laList.add(lstRech.get(i));
           }
       }
       
       
       
       rs.setListTc(laList);
       rs.setNbPage(nbpage);
       
       return rs;
    }
    
    
       int nbPage(List<TableContact> listTc,int separation){
                                        if(listTc.size()<=separation){
                                            return 1;
                                        }else{
                                            if(listTc.size()%separation==0){
                                                return listTc.size()/separation;
                                            }else{
                                                return 1 + listTc.size()/separation;
                                            }
                                        }
                        }
       

}
