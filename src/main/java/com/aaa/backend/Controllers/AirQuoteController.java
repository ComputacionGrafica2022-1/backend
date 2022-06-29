package com.aaa.backend.Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aaa.backend.Models.AirQuote;
// import com.aaa.backend.Services.EmailSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aaa.backend.Services.AirQuoteService;

@RestController
public class AirQuoteController {
    
    @Autowired
    AirQuoteService AirQuoteService;
    // @Autowired
	// private EmailSenderService emailSenderService;

    @GetMapping("/airQuotes")
    public ArrayList<AirQuote> getAllAirQuotes(){
        return AirQuoteService.getAllQuotes();
    }
    
    @PostMapping("/airQuoteFile")
    public AirQuote saveAirQuote(@RequestParam("quote") String airQuoteString, @RequestParam("file") Optional<MultipartFile> file) throws MessagingException, InterruptedException, JsonMappingException, JsonProcessingException{
        AirQuote airQuote = new ObjectMapper().readValue(airQuoteString, AirQuote.class);  
        AirQuote quoteRegistered = AirQuoteService.saveQuote(airQuote);
        if (file.isPresent()){
            try{
                String fileName = file.get().getOriginalFilename();
                // File newFile = new File("C:\\Users\\DIEGO\\Desktop\\AAA\\DWGs\\" + quoteRegistered.getId().toString() + "\\" + fileName);
                File newFile = new File("C:\\Users\\SalaCAD\\Documents\\AAA\\DWGs\\" + quoteRegistered.getId().toString() + "\\" + fileName);
                if(!newFile.exists()){
                    newFile.mkdirs();
                }
                file.get().transferTo( newFile );
            }catch (Exception e){
                System.out.println("ERROR: ".concat(e.toString()));
                return null;
            }
        }
        // emailSenderService.waitToSend(quoteRegistered);
        return quoteRegistered;
    }

    @PostMapping("/airQuote")
    public AirQuote saveAirQuote(@RequestBody AirQuote airQuote){
        AirQuote quoteRegistered = AirQuoteService.saveQuote(airQuote);
        // emailSenderService.waitToSend(quoteRegistered);
        return quoteRegistered;
    }

    @PostMapping("/uploadFile/{id}")
    public ResponseEntity<?> handleUploadFile(@PathVariable("id") Long id, @RequestBody MultipartFile file){
        try{
            String fileName = file.getOriginalFilename();
            // File newFile = new File("C:\\Users\\DIEGO\\Desktop\\AAA\\DWGs\\" + id + "\\" + fileName);
            File newFile = new File("C:\\Users\\SalaCAD\\Documents\\AAA\\DWGs\\" + id + "\\" + fileName);
            if(!newFile.exists()){
                newFile.mkdirs();
            }
            file.transferTo( newFile );
        }catch (Exception e){
            System.out.println("ERROR: ".concat(e.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded succesfully.");
    }

    @PutMapping("/airQuote/{id}")
    public AirQuote updateAirQuote(@PathVariable("id") Long id, @RequestBody AirQuote airQuote){
        if(AirQuoteService.getQuotebyId(id) != null){
            airQuote.setId(id);
            return AirQuoteService.saveQuote(airQuote);
        }
        return null;
    }

    @GetMapping("/airQuote/{id}")
    public AirQuote getAirQuotebyId(@PathVariable("id") Long id){
        return AirQuoteService.getQuotebyId(id);
    }

    @DeleteMapping("/airQuote/{id}")
    public String deleteAirQuotebyId(@PathVariable("id") Long id){
        boolean ok = AirQuoteService.deleteQuote(id);

        if(ok){
            return "Air quote deleted successfully";
        }else{
            return "Failed to delete air quote";
        }
    }
}
