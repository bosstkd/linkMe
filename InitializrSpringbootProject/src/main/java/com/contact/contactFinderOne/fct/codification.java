package com.contact.contactFinderOne.fct;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.apache.commons.codec.binary.Base64;

public class codification {
	
  
	
	
	private  byte[] linebreak = {}; // Remove Base64 encoder default linebreak
	private  String secret = "S2flvjj333@12345!monHotelProject"; // secret key length must be 16
	private  SecretKey key;
	private  Cipher cipher;
	private  Base64 coder;

	 {
				 try {
				     key = new SecretKeySpec(secret.getBytes(), "AES");
				     cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
				     coder = new Base64();
				 } catch (Throwable t) {
				     t.printStackTrace();
				 }
	}
	
	
public  synchronized String encrypt(String plainText) throws Exception {
	       cipher.init(Cipher.ENCRYPT_MODE, key);
	       byte[] cipherText = cipher.doFinal(plainText.getBytes());
	       return  new String(coder.encode(cipherText));
	}

public  synchronized String decrypt(String codedText) throws Exception {
	       byte[] encypted = coder.decode(codedText.getBytes());
	       cipher.init(Cipher.DECRYPT_MODE, key);
	       byte[] decrypted = cipher.doFinal(encypted);  
	       return new String(decrypted);
	}	
	
public  String md5(String plaintext){
	 String hashtext;
	try {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
	    hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
	} catch (NoSuchAlgorithmException e) {
		hashtext = null;
	//	e.printStackTrace();
	}
	return hashtext;
}
	
public  String cd_structure(String str){
	String code = md5(str);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(code.hashCode()/6395))).replaceAll("f", "").toUpperCase();
		code = code +"-"+(""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(code.hashCode()/6415))).replaceAll("f", "").toUpperCase();
	}
	return code;
}


public  String cd_convention(String str, String str2){
	String code = md5(str + str2);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(code.hashCode()/6411))).replaceAll("f", "").toUpperCase();
		code = code +"-"+(""+Integer.toHexString((int)(f_m.hashCode()/6406))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(code.hashCode()/6415))).replaceAll("f", "").toUpperCase();
	}
	return code;
}


public  String cd_morale(String str){
	String code = md5(str);

	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		String mix  = l_m + f_m;
		code = (""+Integer.toHexString((int)(code.hashCode()/6395))).replaceAll("f", "").toUpperCase();
		code = (""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(mix.hashCode()/6395))).replaceAll("f", "").toUpperCase();
	}
	return code;
}

public  String cd_prs(String str){
	String code = md5(str);
	if(str.equals("")){
		code = null;
	}else{
		String f_m  = code.substring(0, 15);
		String l_m  = code.substring(15, 32);
		code = (""+Integer.toHexString((int)(f_m.hashCode()/6395))).replaceAll("f", "").toUpperCase()+"-"+(""+Integer.toHexString((int)(l_m.hashCode()/6395))).replaceAll("f", "").toUpperCase();
	}
	return code;
}

public  String generate(int length)
{
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; // Tu supprimes les lettres dont tu ne veux pas
	    String pass = "";
	    for(int x=0;x<length;x++)
	    {
	       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
	       pass += chars.charAt(i);
	    }
	   // System.out.println(pass);
	    return pass;
}

    /**
     * Web service operation
     */
    @WebMethod(operationName = "operation")
    public String operation(@WebParam(name = "parameter") String parameter) {
        //TODO write your implementation code here:
        return null;
    }


public boolean isGoodPassword(String psw) {
           return psw.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }


public List<Integer> RandomWithDuplicate(int min, int max){
    List<Integer> lstInt = new ArrayList<>();
    for(int i = min; i<=max; i++){
      lstInt.add(min+ (int)(Math.random() * max ));
   }
    return lstInt;
}

public List<Integer> RandomWithoutDuplicate(int min, int max){
  
   int lg;
   List<Integer> tb = new ArrayList<>();
   List<Integer> lstInt = new ArrayList<>();
   for(int i = min; i<=max; i++){
       lstInt.add(i);
   }
   
   while(lstInt.size()>0){
       lg =  min+ (int)(Math.random() * (lstInt.size()) );
       tb.add(lstInt.get(lg-min));
       lstInt.remove(lg-min);
   }
    return tb;
}

public List<Integer> RandomWithoutDuplicate(List<Integer> lstInt){
  
   int lg;
   List<Integer> tb = new ArrayList<>();
      
   while(lstInt.size()>0){
       lg = (int)(Math.random() * (lstInt.size()) );
       tb.add(lstInt.get(lg));
       lstInt.remove(lg);
   }
    return tb;
}

public String psw(){
    
    final List<Integer> AZ = RandomWithoutDuplicate(65, 90);
       
    final List<Integer> az = RandomWithoutDuplicate(97, 122);
                    for(int i = 97; i<=122; i++) az.add(i);
                    
    List<Integer> spc = new ArrayList<>();
                    for(int i = 35; i<=38; i++) spc.add(i);
                    spc.add(43);
                    spc.add(61);
                    spc.add(64);
                    spc.add(94);
    spc = RandomWithoutDuplicate(spc);
                    
    final List<Integer> nm09 = RandomWithoutDuplicate(48, 57);
                     for(int i = 48; i<=57; i++) nm09.add(i);
    
    String psw = "";                 
                   
    List<Integer> order = RandomWithoutDuplicate(1,4);
     int x;
     char c;
    for(int i : order){
        switch(i){
            case 1 :x = AZ.get(0+ (int)(Math.random() * 24 ));
                    break;
            case 2 :x = az.get(0+ (int)(Math.random() * 24 ));
                    break;     
            case 3 :x = spc.get(0+ (int)(Math.random() * 7 ));
                    break;   
            default:x = nm09.get(0+ (int)(Math.random() * 9 ));
                    break; 
        }
            c = (char) x;
            psw = psw + c;
    }
 
   int taille = 1+ (int)(Math.random() * 4 ); 
    
 for(int s=0; s<taille; s++){   
    order = RandomWithDuplicate(1,4);
    for(int i : order){
        switch(i){
            case 1 :x = AZ.get(0+ (int)(Math.random() * 24 ));
                    break;
            case 2 :x = az.get(0+ (int)(Math.random() * 24 ));
                    break;     
            case 3 :x = spc.get(0+ (int)(Math.random() * 7 ));
                    break;   
            default:x = nm09.get(0+ (int)(Math.random() * 9 ));
                    break; 
        }
        c = (char) x;
        psw = psw + c;
    }
}    
    order = RandomWithoutDuplicate(1,4);
        for(int i : order){
            switch(i){
                case 1 :x = AZ.get(0+ (int)(Math.random() * 24 ));
                        break;
                case 2 :x = az.get(0+ (int)(Math.random() * 24 ));
                        break;     
                case 3 :x = spc.get(0+ (int)(Math.random() * 7 ));
                        break;   
                default:x = nm09.get(0+ (int)(Math.random() * 9 ));
                        break; 
            }
             c = (char) x;
             psw = psw +  c;
        }
  
    return psw;
}




private int min(List<Integer> lstInt){
    
    int x = lstInt.get(0);
    
    for(int i : lstInt){
        if(x > i) x = i;
    }
    
    return x;
}

}
