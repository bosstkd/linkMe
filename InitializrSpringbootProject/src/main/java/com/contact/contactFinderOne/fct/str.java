package com.contact.contactFinderOne.fct;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class str {




    
public boolean isName(String str){
    
   Pattern pattern = Pattern.compile("[A-Za-z\\s-_]*");
   Matcher m = pattern.matcher(str);
   
   return m.matches();
}

public String searchForm(String recherche){
    String rst = "";
      
   String traduce = recherche.replaceAll("avec", "et");
    traduce = traduce.replaceAll("'", " ");    
    traduce = traduce.replaceAll("trouve", "");
    traduce = traduce.replaceAll("[^A-Za-z0-9 .,]", "");
    traduce = traduce.replaceAll(",", ".");
    traduce = traduce+" ";
    rst = traduce;
    return rst;
}

public List<String> searchWordList(String phrase){

    String ph = phrase.toLowerCase();
    ph = ph.replaceAll("\\.", "");
    List<String> wrdsList = wordsList(ph);
     List<String> strList = new ArrayList<String>();
     
     
      String str[]=listIgnorWords();
           boolean ok = true;
           for(String word: wrdsList){
                for(int i = 0; i<str.length; i++) 
                          if(word.equals(str[i])){
                                                    ok = false;
                                                    break;
                                                   }
                    if(ok) strList.add(word);
                    ok = true;
            }

    return strList;
}

String[] listIgnorWords(){
    String tab[] = {"est","oui","la","le","je","tu","il","elle", "nous", "vous", "ils", "elles",
                    "qui", "par", "cette", "cet", "sur", "afin", "ainsi", "liste", "list"};
   
    int x = (tab.length);
    String[] tab2= new String[x];
    int j = 0;
    for(int i = 0; i<tab.length; i++){
        tab2[j]=tab[i].toLowerCase();
        j++;
    }
    return tab2;
}

public List<String> searchWordList2(String phrase){

    String ph = phrase.toLowerCase();
    ph = ph.replaceAll("\\.", "");
    List<String> wrdsList = wordsList(ph);
     List<String> strList = new ArrayList<String>();
     
     
      String str[]=listIgnorWords2();
           boolean ok = true;
           for(String word: wrdsList){
                for(int i = 0; i<str.length; i++) 
                          if(word.equals(str[i])){
                                                    ok = false;
                                                    break;
                                                   }
                    if(ok) strList.add(word);
                    ok = true;
            }

    return strList;
}




String[] listIgnorWords2(){
    String tab[] = {"est","oui","la","le","je","tu","il","elle", "nous", "vous", "ils", "elles",
                    "qui", "par", "cette", "cet", "sur", "afin", "ainsi", "liste", "list",
                    "et", "alors", "avec", "comme", "plus", "pourtant", "meme", "des", "de", "du", "en","a",
                    "dans", "lequel", "lieu", "pendant", "sinon", "soit", "ou"};
   
    int x = (tab.length);
    String[] tab2= new String[x];
    int j = 0;
    for(int i = 0; i<tab.length; i++){
        tab2[j]=tab[i].toLowerCase();
        j++;
    }
    return tab2;
}

public List<Double> doubleList(String phrase){
    List<String> strList = wordsList(phrase);
    List<Double> dbList = new ArrayList<Double>();
    nbr NBR = new nbr();
     for(String word:strList){
        if(NBR.isDouble(word)) dbList.add(NBR.toDouble(word));  
    }
     return dbList;
}

public List<Integer> intList(String phrase){
    List<String> strList = wordsList(phrase);
    List<Integer> dbList = new ArrayList<Integer>();
    nbr NBR = new nbr();
     for(String word:strList){
        if(NBR.isInt(word)) dbList.add(NBR.toInt(word));  
    }
     return dbList;
}

public List<String> wordsList(String phrase){
    String getStr = searchForm(phrase);
    List<String> wrdsList = new ArrayList<String>();

    int x = 0;
    while(getStr.contains("  ")) getStr = getStr.replaceAll("  ", " ");
   
    for(int i = 0; i < getStr.length(); i++){
        if((getStr.charAt(i)+"").equals(" ")){
            if(x==0)  
                  wrdsList.add(getStr.substring(0, i));
            else
                  wrdsList.add(getStr.substring(x+1, i));
            x = i;
        }
    }
    
    return wrdsList;
}

public boolean isMail(String str){
   Pattern pattern = Pattern.compile("[A-Za-z0-9\\s-_.]*@[A-Za-z0-9\\s-_.]*.[A-Za-z0-9\\s-_.]{2,3}");
   Matcher m = pattern.matcher(str);
   
   return m.matches();
}

public String nameForm(String str){
	if(!isName(str)){
		str = null;
	}else{
		boolean ok = true;
		str.replaceAll("-", " ");
		str.replaceAll("_", " ");
		
		while(str.substring(0, 1).equals(" ")) {
			try {
				str = str.substring(1, str.length());
			} catch (Exception e) {
				str = null;
				ok = false;
				break;
			}
		}
	if(ok)
		while(str.substring(0, 1).equals("_")) {
			try {
				str = str.substring(1, str.length());
			} catch (Exception e) {
				str = null;
				ok = false;
				break;
			}
		}

	if(ok)
		while(str.contains("  ")) {
			str = str.replaceAll("  ", " ");
		}
	
	if(ok)
		while(str.substring(str.length()-1, str.length()).equals(" "))str = str.substring(0, str.length()-1); 
		
		if(!isName(str)){
			str = null;
			ok = false;
		}
		
		if(ok){
			str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
			for(int i = 0; i<str.length(); i++){
				if(str.substring(i, i+1).equals(" ")){
					
					str = str.substring(0, i+1) + str.substring(i+1, i+2).toUpperCase() + str.substring(i+2, str.length()).toLowerCase();
				}
			}
		}
		
		
	}
	return str;
}

public String strToBdd(String str){
	if(str.contains("'")){
		while(str.substring(0, 1).equals("'")){
			try {
				str = str.substring(1, str.length());	
			} catch (Exception e) {
				str = null;
				break;
			}
		}
		if(str != null){
			while(str.substring(str.length()-1, str.length()).equals("'")){
				try {
					str = str.substring(0 ,str.length()-1);
				} catch (Exception e) {
					str = null;
					break;
				}
			}
			if(str != null){
				if(str.length() > 0){
										str = str.replaceAll("'", "''");
										while(str.contains("'''")) str = str.replaceAll("'''", "''");
									}else{
										str = null;
									}
			}
			
		}
		
		
	}
	return str;
}


public String getInt(String nb){
	String str = "";
	boolean neg = false;
	 for(int i = 0; i < nb.length(); i++){
		 if(i == 0 && nb.substring(0,1).equals("-")) neg = true;
		 try {
			   Integer.parseInt(nb.substring(i, i+1));
			   str = str + nb.substring(i, i+1);
		 } catch (Exception e) {}
	 }
	 if(neg) str = "-"+str;
	return str;
}

public String getFloat(String nb){
	String str = "";
	boolean neg = false;
	 for(int i = 0; i < nb.length(); i++){
		 try {
			 if(i == 0 && nb.substring(0,1).equals("-")) neg = true;
			 if(nb.substring(i, i+1).equals(".")){
				 str = str + nb.substring(i, i+1);
			 }else{
				 Integer.parseInt(nb.substring(i, i+1));
			     str = str + nb.substring(i, i+1);
			 }
			   
		 } catch (Exception e) {}
	 }
	 if(neg) str = "-"+str;
	return str;
}

public boolean isEmpty(String str){
	boolean ok = false;
	if(str.equals("") || str == null)ok = true;
	return ok;
}

public String getWordBetween(String onStr, String str1, String str2){
    if(!onStr.contains(str1) || !onStr.contains(str2)) return "";
    return onStr.substring(onStr.indexOf(str1) + str1.length(), onStr.indexOf(str2));
}

public String getWordBetweenJustNextOne(String onStr, String str1, String str2){
     if(!onStr.contains(str1) || !onStr.contains(str2)) return "";
     return  onStr.substring(onStr.indexOf(str1) + str1.length(), onStr.indexOf(str2, onStr.indexOf(str1)));
}

public String asciiConvert(String toConvert){
    String toReturn = toConvert;
    toReturn= toReturn.replaceAll("&#126;", "~");
    toReturn= toReturn.replaceAll("&#146;", "’");
    toReturn= toReturn.replaceAll("&#231;", "ç");
    toReturn= toReturn.replaceAll("&#232;", "è");
    toReturn= toReturn.replaceAll("&#233;", "é");
    toReturn= toReturn.replaceAll("&#234;", "ê");
    toReturn= toReturn.replaceAll("&#224;", "à");
    toReturn= toReturn.replaceAll("&#225;", "á");
    toReturn= toReturn.replaceAll("&#244;", "ô");
    toReturn= toReturn.replaceAll("&#249;", "ù");
    toReturn= toReturn.replaceAll("&#251;", "û");
    toReturn= toReturn.replaceAll("&#039;", "'");
    toReturn= toReturn.replaceAll("&#044;", ",");
    toReturn= toReturn.replaceAll("&#243;", "ó");
    toReturn= toReturn.replaceAll("&#247;", "÷");
    toReturn= toReturn.replaceAll("&#245;", "õ");
    toReturn= toReturn.replaceAll("&#238", "î");
    toReturn= toReturn.replaceAll("&#235;", "ë");
    toReturn= toReturn.replaceAll("&#237;", "í");
    toReturn= toReturn.replaceAll("&#236;", "ì");
    toReturn= toReturn.replaceAll("&#239;", "ï");
    toReturn= toReturn.replaceAll("&#230;", "æ");
    toReturn= toReturn.replaceAll("&#156;", "œ");
    toReturn= toReturn.replaceAll("&amp;", "&");
    toReturn= toReturn.replaceAll("&amp", "&");
    toReturn= toReturn.replaceAll("&#8211;","–");
    toReturn= toReturn.replaceAll("&bull;", "•");
        
    return toReturn;
}



}
