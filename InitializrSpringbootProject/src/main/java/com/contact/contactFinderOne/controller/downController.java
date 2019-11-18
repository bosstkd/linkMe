package com.contact.contactFinderOne.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.contact.contactFinderOne.entities.TableContact;
import com.contact.contactFinderOne.repository.tableContactRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class downController {


	@Autowired
	private tableContactRepository tcr;
	
    @GetMapping("/download/{id}/{ip}/{req}")
    public ResponseEntity<byte[]> getSearchResult(@PathVariable String id,@PathVariable String ip,@PathVariable String req, HttpServletResponse response){		

        List<TableContact> lstRech = tcr.findByRequest(req);

        StringBuffer buffer = new StringBuffer();
        buffer.append("("+req+") "+id+" sous ip :"+ip+", (list des emails) :");
        buffer.append(System.getProperty("line.separator"));
          for(TableContact rch: lstRech){
                         buffer.append(rch.getMail());
                         buffer.append(System.getProperty("line.separator"));
                    }  
      
        byte[] bytes = buffer.toString().getBytes();
               
       return ResponseEntity.ok().body(bytes);
    }
    
	
}


/*
  @GetMapping("/download/{id}/{ip}/{req}")
    public ResponseEntity<byte[]> getSearchResult(@PathVariable String id,@PathVariable String ip,@PathVariable String req, HttpServletResponse response) throws IOException {		

        List<TableContact> lstRech = tcr.findByRequest(req);
      
        String fileName = new codification().cd_prs(id)+".txt";
        
            try (PrintWriter writer = new PrintWriter(fileName, "UTF-8")) {
                    writer.println("("+req+") "+id+" sous ip :"+ip+", (list des emails) :");
                    for(TableContact rch: lstRech){
                        writer.println(rch.getMail());
                    }  
                    writer.close();
            }
        File initialFile = new File(fileName);
       
        try {
            // copy it to response's OutputStream
            try ( // get your file as InputStream
                    InputStream is = new FileInputStream(initialFile)) {
                // copy it to response's OutputStream
                org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
                response.flushBuffer();
            }
          } catch (IOException ex) {
           throw new RuntimeException("IOError writing file to output stream");
          }
      
        URLConnection connection = initialFile.toURL().openConnection();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.valueOf(connection.getContentType()));
        header.setContentLength(initialFile.length());
        header.set("Content-Disposition", "attachment; filename="+initialFile.getName());
       
        
        StringBuffer buffer = new StringBuffer();
        //String line;

        //BufferedReader in = new BufferedReader(new FileReader(initialFile.getName()));
        buffer.append("("+req+") "+id+" sous ip :"+ip+", (list des emails) :");
        
          for(TableContact rch: lstRech){
                         buffer.append(rch.getMail());
                         buffer.append("\n");
                    }  
       
        while ((line = in.readLine()) != null) {
           buffer.append(line);
           buffer.append("\n");
        }
        
       // System.out.println(initialFile.getAbsolutePath());
        byte[] bytes = buffer.toString().getBytes();
        
       // initialFile.delete();
       
       return ResponseEntity.ok().body(bytes);
    }
*/