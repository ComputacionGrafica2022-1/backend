package com.aaa.backend.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aire")
public class AirQuote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String userEmail;
    // Masa de aire
    private Double mass;
    // Longitud de ductos
    private Double length;
    // Grosor de lámina
    private Double thickness;
    // Número de difusores
    private Integer diffusers;
    // Tipo de aire acondicionado
    //  1. De pared
    //  2. Unidad externa
    private Integer type;
    private Boolean done;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getMass() {
        return this.mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getLength() {
        return this.length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getThickness() {
        return this.thickness;
    }

    public void setThickness(Double thickness) {
        this.thickness = thickness;
    }

    public Integer getDiffusers() {
        return this.diffusers;
    }

    public void setDiffusers(Integer diffusers) {
        this.diffusers = diffusers;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getDone() {
        return this.done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
   
}
