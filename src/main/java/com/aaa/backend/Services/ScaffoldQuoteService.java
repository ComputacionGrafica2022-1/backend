package com.aaa.backend.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.backend.Models.ScaffoldQuote;
import com.aaa.backend.Repositories.ScaffoldQuoteRepository;

@Service
public class ScaffoldQuoteService {
    
    @Autowired
    ScaffoldQuoteRepository scaffoldQuoteRepository;

    public ArrayList<ScaffoldQuote> getAllQuotes(){
        return (ArrayList<ScaffoldQuote>) scaffoldQuoteRepository.findAll();
    }

    public ScaffoldQuote saveQuote(ScaffoldQuote quote){
        return scaffoldQuoteRepository.save(quote);
    }
    
    public ScaffoldQuote getQuotebyId(Long id){
        return scaffoldQuoteRepository.findById(id).orElse(null);
    }

    public boolean deleteQuote(Long id){
        try{
            scaffoldQuoteRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
