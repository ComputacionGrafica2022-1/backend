package com.aaa.backend.Services;

import java.util.ArrayList;

import com.aaa.backend.Models.Supplier;
import com.aaa.backend.Repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public ArrayList<Supplier> getAllSuppliers(){
        return (ArrayList<Supplier>) supplierRepository.findAll();
    }

    public Supplier saveSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }
    
    public Supplier getSupplierbyId(Long id){
        return supplierRepository.findById(id).orElse(null);
    }

    public boolean deleteSupplier(Long id){
        try{
            supplierRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
