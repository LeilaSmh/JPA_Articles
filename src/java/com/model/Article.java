/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Dell
 */

@Entity
@Table
@NamedQueries({@NamedQuery(name="Article.getAll", query="SELECT e FROM Article e")})
public class Article implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private int code;
    @Column
    private String designation;
    @Column
    private double prix;

    public Article(int code, String designation, double prix) {
        this.code = code;
        this.designation = designation;
        this.prix = prix;
    }
  
    public Article() {
        
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
}
