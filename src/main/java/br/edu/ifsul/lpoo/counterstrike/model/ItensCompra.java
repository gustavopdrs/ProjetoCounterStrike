/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_itenscompra")
public class ItensCompra {
    
    @Id
    @SequenceGenerator(name = "seq_itenscompra", sequenceName = "seq_itenscompra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_itenscompra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(length = 8, nullable = false)
    private Integer quantidade;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    @ManyToOne
    @JoinColumn(name = "artefato_id", nullable = false)
    private Artefato Artefato;
    
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;
    
    public ItensCompra(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Artefato getArtefato() {
        return Artefato;
    }

    public void setArtefato(Artefato Artefato) {
        this.Artefato = Artefato;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    
    
}
