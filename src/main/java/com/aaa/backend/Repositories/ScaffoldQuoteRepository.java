package com.aaa.backend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aaa.backend.Models.ScaffoldQuote;

@Repository
public interface ScaffoldQuoteRepository extends CrudRepository<ScaffoldQuote, Long> {
    
}
