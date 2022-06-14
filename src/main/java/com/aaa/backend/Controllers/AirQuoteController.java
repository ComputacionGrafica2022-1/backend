package com.aaa.backend.Controllers;

import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aaa.backend.Models.AirQuote;
import com.aaa.backend.Services.EmailSenderService;
import com.aaa.backend.Services.AirQuoteService;

@RestController
public class AirQuoteController {
    
    @Autowired
    AirQuoteService AirQuoteService;
    @Autowired
	private EmailSenderService emailSenderService;

    @GetMapping("/airQuotes")
    public ArrayList<AirQuote> getAllAirQuotes(){
        return AirQuoteService.getAllQuotes();
    }
    
    @PostMapping("/airQuote")
    public AirQuote saveAirQuote(@RequestBody AirQuote airQuote) throws MessagingException, InterruptedException{
        AirQuote quoteRegistered = AirQuoteService.saveQuote(airQuote);
        emailSenderService.waitToSend(quoteRegistered);
        return quoteRegistered;
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
