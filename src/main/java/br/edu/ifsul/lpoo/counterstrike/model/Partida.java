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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_partida")
public class Partida implements Serializable{
    
        @Id
    @SequenceGenerator(name = "seq_partida", sequenceName = "seq_partida_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_partida", strategy = GenerationType.SEQUENCE)
    private Integer id;
        
            @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_inicio;
    
        @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_fim;
    
//        @OneToMany(mappedBy = "partida") // o nome "produto" referencia o atributo produto na classe Foto
//    private List<Round> rounds;
        
           @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_jogadores", joinColumns = {@JoinColumn(name = "partida_id")}, 
                                       inverseJoinColumns = {@JoinColumn(name = "jogador_id")})
    private List<Jogador> jogadores;
    
    public Partida(){
        
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

//    public List<Round> getRounds() {
//        return rounds;
//    }
//
//    public void setRounds(List<Round> rounds) {
//        this.rounds = rounds;
//    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    
    
    
}
