/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_municao")
@DiscriminatorValue("MU")
public class Municao extends Artefato{
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = false, length = 40)
    private Integer qtdPente;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Calibre calibre;
    
    public Municao(){
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdPente() {
        return qtdPente;
    }

    public void setQtdPente(Integer qtdPente) {
        this.qtdPente = qtdPente;
    }

    public Calibre getCalibre() {
        return calibre;
    }

    public void setCalibre(Calibre calibre) {
        this.calibre = calibre;
    }
    
    
}
