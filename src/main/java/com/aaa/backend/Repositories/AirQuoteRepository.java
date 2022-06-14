package com.aaa.backend.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aaa.backend.Models.AirQuote;

@Repository
public interface AirQuoteRepository extends CrudRepository<AirQuote, Long> {
    
}
