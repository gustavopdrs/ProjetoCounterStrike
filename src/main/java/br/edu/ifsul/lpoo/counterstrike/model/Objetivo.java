/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import java.io.Serializable;
import java.util.ArrayList;
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
@Table(name = "tb_objetivo")
public class Objetivo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_objetivo", sequenceName = "seq_objetivo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_objetivo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false, length = 100)
    private Integer pontos;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_locais_objetivos", joinColumns = {
        @JoinColumn(name = "objetivo_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "local_id")})
    private List<Local> locais;

    public Objetivo() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public List<Local> getLocais() {
        return locais;
    }

    public void setLocais(List<Local> locais) {
        this.locais = locais;
    }

    public void setLocal(Local local) {
        if (this.locais == null) {
            this.locais = new ArrayList();
        }
        this.locais.add(local);
    }

}
