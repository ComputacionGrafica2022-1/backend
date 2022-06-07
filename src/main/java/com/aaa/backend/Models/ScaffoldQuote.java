package com.aaa.backend.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "andamio")
public class ScaffoldQuote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String userEmail;
    private Double width;
    private Double depth;
    private Integer levels;
    private Double levelHeight;
    private Boolean wheels;
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

    public Double getWidth() {
        return this.width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getDepth() {
        return this.depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Integer getLevels() {
        return this.levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Double getLevelHeight() {
        return this.levelHeight;
    }

    public void setLevelHeight(Double levelHeight) {
        this.levelHeight = levelHeight;
    }

    public Boolean getWheels() {
        return this.wheels;
    }

    public void setWheels(Boolean wheels) {
        this.wheels = wheels;
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
