/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_artefato")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipoArtefato")
public class Artefato implements Serializable{
   
    @Id
    @SequenceGenerator(name = "seq_artefato", sequenceName = "seq_artefato_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_artefato", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    
    public Artefato(){
        
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    
    
}
