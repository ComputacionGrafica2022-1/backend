package com.aaa.backend.Controllers;

import java.util.ArrayList;

import javax.mail.MessagingException;

import com.aaa.backend.Models.Supplier;
import com.aaa.backend.Services.EmailSenderService;
import com.aaa.backend.Services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupplierController {
    
    @Autowired
    SupplierService supplierService;
    @Autowired
	private EmailSenderService emailSenderService;

    @GetMapping("/suppliers")
    public ArrayList<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
    
    @PostMapping("/supplier")
    public Supplier saveSupplier(@RequestBody Supplier supplier) throws MessagingException, InterruptedException{
        Supplier supplierRegistered = supplierService.saveSupplier(supplier);
        emailSenderService.waitToSend(supplierRegistered);
        return supplierRegistered;
    }

    @PutMapping("/supplier/{id}")
    public Supplier updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier){
        if(supplierService.getSupplierbyId(id) != null){
            supplier.setId(id);
            return supplierService.saveSupplier(supplier);
        }
        return null;
    }

    @GetMapping("/supplier/{id}")
    public Supplier getSupplierbyId(@PathVariable("id") Long id){
        return supplierService.getSupplierbyId(id);
    }

    @DeleteMapping("/supplier/{id}")
    public String deleteSupplierbyId(@PathVariable("id") Long id){
        boolean ok = supplierService.deleteSupplier(id);

        if(ok){
            return "Supplier deleted successfully";
        }else{
            return "Failed to delete supplier";
        }
    }

}
