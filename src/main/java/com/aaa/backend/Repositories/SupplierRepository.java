package com.aaa.backend.Repositories;

import com.aaa.backend.Models.Supplier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    
}
