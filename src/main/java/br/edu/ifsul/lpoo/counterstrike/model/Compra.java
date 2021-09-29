/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_compra")
public class Compra implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_compra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @Column(precision = 2, nullable = false)
    private Float valorTotal;
    
    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;
    
    @OneToMany(mappedBy = "compra") // o nome "produto" referencia o atributo produto na classe Foto
    private List<ItensCompra> itenscompra;

    public Compra(){
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public List<ItensCompra> getItenscompra() {
        return itenscompra;
    }

    public void setItenscompra(List<ItensCompra> itenscompra) {
        this.itenscompra = itenscompra;
    }
    
    
}
