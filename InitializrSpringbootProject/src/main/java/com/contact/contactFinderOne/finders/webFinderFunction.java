package com.contact.contactFinderOne.finders;

import com.contact.contactFinderOne.fct.str;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Amine
 */
public class webFinderFunction {
    
    /*
    public static void main(String ags[]) throws IOException{
        webFinderFunction wff = new webFinderFunction();
        
        List<myContactsBean> ls = wff.getContactMailFromQuestion("hotel constantine", 2, 0);
        
        for(myContactsBean urlReq:ls){
            System.out.println("Mail: "+urlReq.getContactsLink()+"\t  TITRE:"+urlReq.getTitre()+"\t  TAGS:"+urlReq.getTags()+"\t  DESCRIPTION:"+urlReq.getPresentation()+"\t  Link : "+urlReq.getUrl());
        }  
    }
    */

//---------- N'ajoute pas un element existant-------------
public List<String> distinctStringList(List<String> lstStr){
    List<String> lst = new ArrayList<String>();
    for(String str : lstStr){
        if(!elementExist(lst, str))lst.add(str);
    }
    return lst;
}

//---------- N'ajoute pas un element existant-------------
public List<myContactsBean> distinctMyMailsList(List<myContactsBean> lstStr){
    List<myContactsBean> lst = new ArrayList<>();
    List<String> mailList = new ArrayList<>();
    
    
    for(myContactsBean str : lstStr){
         if(!mailList.contains(str.getContactsLink())){
                                        lst.add(str);
                                        mailList.add(str.getContactsLink());
         }
    }
    return lst;
}

//---------- Confirmation d'existance -------------
public boolean elementExist(List<String> lstStr, String str){
    for(String st : lstStr){
        if(st.equals(str))return true;
    }
    return false;
}

//---------- Ajouter une liste a une autre---------------------
public void addListToList(List<String> lst1, List<String> lst2){
     for(String st : lst2){
         lst1.add(st);
    }
}

//-------- Liste des requette de recherche-------------------
public List<String> searchRequestList(String question, int profondeur){

 List<String> lstUrls = new ArrayList<String>();
 str mot = new str();
 List<String> req = mot.searchWordList(question);
 List<Integer> intlst = mot.intList(question);
 String ints = "";
 
 for(Integer intt:intlst){
     ints = ints+"+"+intt;
 }
 
 for(int i = 0; i<=profondeur; i++){
     String wordToInsert = "";
     for(String st:req){
             wordToInsert = wordToInsert+"+"+st;
     }

     
     String urlBing = "https://www.bing.com/search?q=";
     
     wordToInsert = wordToInsert.substring(1, wordToInsert.length());
     if(i==0){
         lstUrls.add(urlBing+wordToInsert+ints);
     }else if(i>1){
         lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PERE"+(i-1)); 
         lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PORE"+(i-1)); 
         lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PQRE"+(i-1)); 
          
          int s =(int) (Math.random() * (0-i) + 1);
          lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PERE"+s); 
          s =(int) (Math.random() * (0-i) + 1);
          lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PORE"+s); 
          s =(int) (Math.random() * (0-i) + 1);
          lstUrls.add(urlBing+wordToInsert+ints+"&first="+i+"1&FORM=PQRE"+s); 
     }else{
         lstUrls.add(urlBing+wordToInsert+ints+"&first=1&FORM=PERE"); 
     }
 }
   
 return   distinctStringList(lstUrls);
}
    
//-------- Liste des MAILS depuis une URL --------------------
public List<String> mailsGetterFromUrl(String url){
    String sourceCode = "";
				try {
                                    sourceCode = codeSourcePage(url);
					//System.out.println(codeSourcePage(url));
				} catch (IOException e) { }
                                
				List<String> listHttpLoc = new ArrayList<>();
				try {
					listHttpLoc = listMailFromUrl(sourceCode);
				} catch (IOException e) {}
				Vector vct = new Vector();
                                String str[]=listIgnorWords();
                                boolean ok = true;
                                for(String url1: listHttpLoc){
                                    for(int i = 0; i<str.length; i++) if(url1.contains(str[i])) ok = false;
                                    if(ok) vct.addElement(url1);
                                    ok = true;
                                }
                                List<String> strList = new ArrayList<String>();
				for(int i = 0; i<vct.size(); i++) 
                                    strList.add((String)vct.elementAt(i));
                                
                     List<String> lstMails = (strList);         
                                
            return distinctStringList(lstMails);
}

//-------- Liste des Phones depuis une URL --------------------
public List<String> phonesGetterFromUrl(String url){
    String sourceCode = "";
				try {
                                    sourceCode = codeSourcePage(url);
					//System.out.println(codeSourcePage(url));
				} catch (IOException e) { }
                                
				List<String> listHttpLoc = new ArrayList<>();
				try {
					listHttpLoc = listPhoneFromUrl(sourceCode);
				} catch (IOException e) {}
				Vector vct = new Vector();
                                String str[]=listIgnorWords();
                                boolean ok = true;
                                for(String url1: listHttpLoc){
                                    for(int i = 0; i<str.length; i++) if(url1.contains(str[i])) ok = false;
                                    if(ok) vct.addElement(url1);
                                    ok = true;
                                }
                                List<String> strList = new ArrayList<String>();
				for(int i = 0; i<vct.size(); i++) 
                                    strList.add((String)vct.elementAt(i));
                                
                     List<String> lstMails = (strList);         
                                
            return distinctStringList(lstMails);
}

//-------- Liste des URLS depuis une URL --------------------
public List<String> urlsGetterFromUrl(String url){
    String sourceCode = "";
				try {
                                    sourceCode = codeSourcePage(url);
					//System.out.println(codeSourcePage(url));
				} catch (IOException e) { }
                                
				List<String> listHttpLoc = new ArrayList<>();
				try {
					listHttpLoc = listHttpGetter(sourceCode);
				} catch (IOException e) {}
				Vector vct = new Vector();
                                String str[]=listIgnorWords();
                                boolean ok = true;
                                for(String url1: listHttpLoc){
                                    for(int i = 0; i<str.length; i++) if(url1.contains(str[i])) ok = false;
                                    if(ok) vct.addElement(url1);
                                    ok = true;
                                }
                                List<String> strList = new ArrayList<String>();
				for(int i = 0; i<vct.size(); i++) 
                                    strList.add((String)vct.elementAt(i));
                                
                     List<String> lstUrls = (strList);         
                                
            return distinctStringList(lstUrls);
}

//-------------- Code source URL-----------------
public String codeSourcePage(String url) throws IOException{
	 String generate_URL = url;
         String result = "";
         String inputLine;
        try {
            URL data = new URL(generate_URL);
           
            HttpURLConnection con = (HttpURLConnection) data.openConnection(); 
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF8"));
            while ((inputLine = in.readLine()) != null) {
                result = result+"\n"+inputLine;
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
           // e.printStackTrace();
        }
        return result;
}

//------------- Liste des HTTPs dans un text ----------------------
List<String> listHttpGetter(String page) throws IOException{
	 Matcher m = Pattern.compile("\\b(http|https|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]").matcher(page);
	    
	    List<String> lst = new ArrayList<String>();
	    
	    while (m.find()) {
	        lst.add(m.group());
	    }
	    
	    return lst;
}

//------------- Liste des mots a ignorer -----------------
String[] listIgnorWords(){
    String tab[] = {"schema.org/SearchResultsPage","instagram","bing","facebook","viamichelin",
                    "webcache.googleusercontent","mailto:robert@broofa.com","w3.org",".webm","tripadvisor",
                    "google", ".svg", ".js", ".ico", ".png", ".jpg", ".doc", ".jar", ".exe",".css",".gif","jumia",
                    ".rar", ".zip",".pdf","schemas","microsoft", "xhtml", "schema.org", "linkedin.com","trivago","booking",
                    ".woff2", ".3GP", ".7Z", ".AAC", ".ACE", ".AIF", ".ARJ", ".ASF", ".AVI", ".BIN", ".BZ2", ".EXE", 
                    ".GZ", ".GZIP", ".IMG", ".ISO", ".LZH", ".M4A", ".M4V", ".MKV", ".MOV", ".MPA", ".MPE", ".MPEG", 
                    ".MPG", ".MSI", ".MSU", ".OGG", ".OGV", ".PLJ", ".PPS", ".PPT", ".QT", ".R0*", ".R1*", ".RA", ".RAR", 
                    ".RM", ".SEA", ".SIT", ".SITX", ".TAR", ".TIF", ".TIFF", ".WAV", ".WMA", ".WMV", ".Z", ".ZIP", ".MP4", ".MP3"};
   
    int x = (tab.length)*2;
    String[] tab2= new String[x];
    int j = 0;
    for(int i = 0; i<tab.length; i++){
        tab2[j]=tab[i].toLowerCase();
        j++;
        tab2[j]=tab[i].toUpperCase();
        j++;
    }
    return tab2;
}

//------------- Liste all urls from searchRequest -----------------
public List<String> listOfAllRequestedUrls(String question, int profondeur){
    List<String> lstUrls = new ArrayList<String>();
    
    List<String> lstSearch = searchRequestList(question, profondeur);
    
    for(String strSearch:lstSearch){
        List<String> lstUrls1 = urlsGetterFromUrl(strSearch);
        addListToList(lstUrls, lstUrls1);
    }
    return distinctStringList(lstUrls);
}


//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//************* Liste contacts Url from listOfAllRequestedUrls *******
public List<myContactsBean> getContactMailFromQuestion(String Question, int profondeur, int searchType) throws IOException{
    
List<myContactsBean> MML = new ArrayList<myContactsBean>();    
    
List<String> lstBing = new ArrayList<>();
addListToList(lstBing, listOfAllRequestedUrls(Question, profondeur) );

List<String> lstLink1 = new ArrayList<>();
List<String> lstLink2Contact = new ArrayList<>();

List<String> lstToRequestAllLinks = new ArrayList<>();
List<String> lstMail = new ArrayList<>();

//----- bing level ------
lstBing = MyListFiltring(lstBing);

if(lstBing != null){
    for(String bing: lstBing){
        try {
            System.out.println("bing: "+bing);
            addListToList(lstLink1, urlsGetterFromUrl(bing)); 
        } catch (Exception e) {
            System.out.println("bing level: "+e);
        }
    }
    
  //----- lstLink1 level ------  
     lstLink1 = MyListFiltring(lstLink1);

        if(lstLink1 != null){
            
            for(String link1: lstLink1)
                        if(link1.contains("ontac") || link1.contains("ONTAC"))
                           try {
                                System.out.println("link1: "+link1);
                                addListToList(lstLink2Contact, urlsGetterFromUrl(link1)); 
                            } catch (Exception e) {
                                System.out.println("link 1 level: "+e);
                            } 
                        

        }
        
  //----- lstToRequestAllLinks level ----      
    addListToList(lstToRequestAllLinks, lstBing);
   // addListToList(lstToRequestAllLinks, lstLink1);
    addListToList(lstToRequestAllLinks, lstLink2Contact);
    
     lstToRequestAllLinks = MyListFiltring(lstToRequestAllLinks);
     
     if(lstToRequestAllLinks != null){
         for(String globalLink: lstToRequestAllLinks){
              System.out.println("globalLink: "+globalLink);
             
             if(searchType == 0){
                 for(String mail :  mailsGetterFromUrl(globalLink)){
                            System.out.println("mail: "+mail);
                          //  System.out.println("titre: "+titreUrl(globalLink));
                          //  System.out.println("tags: "+tagsUrl(globalLink));
                          //  System.out.println("description: "+descriptionUrl(globalLink));
                          
                            lstMail.add(mail);
                            MML.add(new myContactsBean(globalLink, mail, titreUrl(globalLink), " ", " "));
                          //  MML.add(new myContactsBean(globalLink, mail, titreUrl(globalLink), tagsUrl(globalLink), descriptionUrl(globalLink)));
                 }
             }
            
             else if(searchType == 1){
                for(String phone : phonesGetterFromUrl(globalLink)){
                            System.out.println("titre: "+titreUrl(globalLink));
                            System.out.println("tags: "+tagsUrl(globalLink));
                            System.out.println("description: "+descriptionUrl(globalLink));
                            System.out.println("phone: "+phone);
                            
                            lstMail.add(phone);
                            MML.add(new myContactsBean(globalLink, phone, titreUrl(globalLink), tagsUrl(globalLink), descriptionUrl(globalLink)));

                } 
             }else{
                 for(String mail :  mailsGetterFromUrl(globalLink)){
                            System.out.println("mail: "+mail);
                            System.out.println("titre: "+titreUrl(globalLink));
                            System.out.println("tags: "+tagsUrl(globalLink));
                            System.out.println("description: "+descriptionUrl(globalLink));
                            
                            lstMail.add(mail);
                            MML.add(new myContactsBean(globalLink, mail, titreUrl(globalLink), tagsUrl(globalLink), descriptionUrl(globalLink)));
                        }
                 
                 for(String phone : phonesGetterFromUrl(globalLink)){
                            System.out.println("titre: "+titreUrl(globalLink));
                            System.out.println("tags: "+tagsUrl(globalLink));
                            System.out.println("description: "+descriptionUrl(globalLink));
                            System.out.println("phone: "+phone);

                            lstMail.add(phone);
                            MML.add(new myContactsBean(globalLink, phone, titreUrl(globalLink), tagsUrl(globalLink), descriptionUrl(globalLink)));
                } 
             }
             
             
         }
     }
}

    return distinctMyMailsList(MML) ;
}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


//------------- Liste Mails Creator from URL ----------------------
List<String> listMailFromUrl(String page) throws IOException{
            Matcher m = Pattern.compile("[a-zA-A-Z0-9_%.+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9-.]+").matcher(page);	    
	    List<String> lst = new ArrayList<String>();
	    
	    while (m.find()) {
	        lst.add(m.group());
	    }
	    
	    return lst;
}

//------------- Liste Mails Creator from URL ----------------------
List<String> listPhoneFromUrl(String page) throws IOException{
            Matcher m = Pattern.compile("(?:(?:\\+|00)33[\\s.-]{0,3}(?:\\(0\\)[\\s.-]{0,3})?|0)[1-9](?:(?:[\\s.-]?\\d{2}){4}|\\d{2}(?:[\\s.-]?\\d{3}){2})").matcher(page);	    
	    List<String> lst = new ArrayList<String>();
	    
	    while (m.find()) {
	        lst.add(m.group());
	    }
	    
	    return lst;
}

//-----------------Filtration par list ignorer et r�pitition-----
public List<String> MyListFiltring(List<String> lstUrls){
   
    String IgnorTab[]=listIgnorWords();

    for(int i = 0; i<lstUrls.size(); i++){
        for(String str:IgnorTab){
            if(lstUrls.get(i).contains(str)){
                lstUrls.set(i, "*");
            }
        }
    }
    List<String> lstUrlsFilter = new ArrayList<>();
    
    for(String str: lstUrls){
        if(!str.equals("*"))lstUrlsFilter.add(str);
    }
    lstUrlsFilter = distinctStringList(lstUrlsFilter);
    return lstUrlsFilter;
}

//-----------------Titre du l'URL---------------------------------
public String titreUrl(String url){
    String sourceCode = "";
    try { sourceCode = codeSourcePage(url);} catch (IOException e) { }
    
    return new str().getWordBetween(sourceCode, "<title>", "</title>");
}

public String tagsUrl(String url){
    String sourceCode = "";
    try { sourceCode = codeSourcePage(url);} catch (IOException e) { }
         Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\p{L} _-|]+$");
         String str = new str().getWordBetweenJustNextOne(sourceCode, "description", "/>");
        
        str = str.replaceAll("\"", " ");
        str = str.replaceAll("content", "");
        str = str.replaceAll(".", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("=", "");
        
         Matcher m = pattern.matcher(str);
        
         if(m.matches()) return str;
            return "";
    }

public String descriptionUrl(String url){
    String sourceCode = "";
    try { sourceCode = codeSourcePage(url);} catch (IOException e) { }
         Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\p{L} _-|]+$");
        String str = new str().getWordBetweenJustNextOne(sourceCode, "keywords", "/>");
        str = str.replaceAll("\"", " ");
        str = str.replaceAll("content", "");
        str = str.replaceAll(".", " ");
        str = str.replaceAll(",", " ");
        str = str.replaceAll("=", "");
          Matcher m = pattern.matcher(str);
        
        if(m.matches()) return str;
        return "";
}

}
