/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_local")
public class Local implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_local", sequenceName = "seq_local_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_local", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(nullable = false, length = 200)
    private String latitude;
    
    @Column(nullable = false, length = 200)
    private String longitude;
    
    public Local(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    
    
    
}
