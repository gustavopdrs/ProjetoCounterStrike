/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_arma")
@DiscriminatorValue("AR")
public class Arma extends Artefato {

    @Column(nullable = false, length = 200)
    private String descricao;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_municoes", joinColumns = {
        @JoinColumn(name = "arma_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "municao_id")})
    private List<Municao> municoes;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public Arma() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Municao> getMunicoes() {
        return municoes;
    }

    public void setMunicoes(List<Municao> municoes) {
        this.municoes = municoes;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
