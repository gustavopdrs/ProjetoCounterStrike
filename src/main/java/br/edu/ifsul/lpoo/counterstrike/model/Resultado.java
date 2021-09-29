/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tb_resultado")
public class Resultado implements Serializable{
    
        @Id
    @SequenceGenerator(name = "seq_resultado", sequenceName = "seq_resultado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_resultado", strategy = GenerationType.SEQUENCE)
    private Integer Id;
        
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar info_data;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "round_id", nullable = false) 
    private Round round;
        
    @ManyToOne
    @JoinColumn(name = "objetivo_id", nullable = false) 
    private Objetivo objetivo;
    
    public Resultado(){
        
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Calendar getInfo_data() {
        return info_data;
    }

    public void setInfo_data(Calendar info_data) {
        this.info_data = info_data;
    }


    
    
}
