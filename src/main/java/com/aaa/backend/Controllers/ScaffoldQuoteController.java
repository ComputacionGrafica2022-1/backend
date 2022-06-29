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

import com.aaa.backend.Models.ScaffoldQuote;
// import com.aaa.backend.Services.EmailSenderService;
import com.aaa.backend.Services.ScaffoldQuoteService;

@RestController
public class ScaffoldQuoteController {
    
    @Autowired
    ScaffoldQuoteService scaffoldQuoteService;
    // @Autowired
	// private EmailSenderService emailSenderService;

    @GetMapping("/scaffoldQuotes")
    public ArrayList<ScaffoldQuote> getAllScaffoldQuotes(){
        return scaffoldQuoteService.getAllQuotes();
    }
    
    @PostMapping("/scaffoldQuote")
    public ScaffoldQuote saveScaffoldQuote(@RequestBody ScaffoldQuote scaffoldQuote) throws MessagingException, InterruptedException{
        ScaffoldQuote quoteRegistered = scaffoldQuoteService.saveQuote(scaffoldQuote);
        // emailSenderService.waitToSend(quoteRegistered);
        return quoteRegistered;
    }

    @PutMapping("/scaffoldQuote/{id}")
    public ScaffoldQuote updateScaffoldQuote(@PathVariable("id") Long id, @RequestBody ScaffoldQuote scaffoldQuote){
        if(scaffoldQuoteService.getQuotebyId(id) != null){
            scaffoldQuote.setId(id);
            return scaffoldQuoteService.saveQuote(scaffoldQuote);
        }
        return null;
    }

    @GetMapping("/scaffoldQuote/{id}")
    public ScaffoldQuote getScaffoldQuotebyId(@PathVariable("id") Long id){
        return scaffoldQuoteService.getQuotebyId(id);
    }

    @DeleteMapping("/scaffoldQuote/{id}")
    public String deleteScaffoldQuotebyId(@PathVariable("id") Long id){
        boolean ok = scaffoldQuoteService.deleteQuote(id);

        if(ok){
            return "Scaffold quote deleted successfully";
        }else{
            return "Failed to delete scaffold quote";
        }
    }
}
