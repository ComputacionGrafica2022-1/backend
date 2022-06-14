package com.aaa.backend.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.backend.Models.AirQuote;
import com.aaa.backend.Repositories.AirQuoteRepository;

@Service
public class AirQuoteService {
    
    @Autowired
    AirQuoteRepository airQuoteRepository;

    public ArrayList<AirQuote> getAllQuotes(){
        return (ArrayList<AirQuote>) airQuoteRepository.findAll();
    }

    public AirQuote saveQuote(AirQuote quote){
        return airQuoteRepository.save(quote);
    }
    
    public AirQuote getQuotebyId(Long id){
        return airQuoteRepository.findById(id).orElse(null);
    }

    public boolean deleteQuote(Long id){
        try{
            airQuoteRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
