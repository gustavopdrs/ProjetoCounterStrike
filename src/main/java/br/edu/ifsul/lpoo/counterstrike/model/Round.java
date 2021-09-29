/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_round")
public class Round implements Serializable{
    
        @Id
    @SequenceGenerator(name = "seq_round", sequenceName = "seq_round_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_round", strategy = GenerationType.SEQUENCE)
    private Integer id;
        
         @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)   
    private Calendar data_inicio;
    
        @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_fim;
    
        @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_objetivos", joinColumns = {@JoinColumn(name = "round_id")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "objetivos_id")})
    private List<Objetivo> objetivos;
        
        @Column(nullable = false)
    @Enumerated(EnumType.STRING)    
    private Modo modo;
        
//         @ManyToOne
//    @JoinColumn(name = "partida_id", nullable = false)   
//    private Partida partida;
    
    public Round(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Calendar getData_fim() {
        return data_fim;
    }

    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    public List<Objetivo> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<Objetivo> objetivos) {
        this.objetivos = objetivos;
    }

    public Modo getModo() {
        return modo;
    }

    public void setModo(Modo modo) {
        this.modo = modo;
    }

//    public Partida getPartida() {
//        return partida;
//    }
//
//    public void setPartida(Partida partida) {
//        this.partida = partida;
//    }
    
    
}
