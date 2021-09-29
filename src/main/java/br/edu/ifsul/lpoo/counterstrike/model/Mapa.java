/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_mapa")
public class Mapa implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_mapa", sequenceName = "seq_mapa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_mapa", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
        @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_locais", joinColumns = {@JoinColumn(name = "mapa_id")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "local_id")})
    private List<Local> locais;
    
    public Mapa(){
        
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

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }
    
    
}
