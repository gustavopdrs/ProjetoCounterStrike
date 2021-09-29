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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gugag
 */
@Entity
@Table(name = "tb_jogador")
public class Jogador implements Serializable {

    @Id
    private String nickname;

    @Column(nullable = false, length = 40)
    private String senha;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_cadastro;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultimo_login;

    @Column(nullable = false, length = 200)
    private Integer pontos;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_artefatos", joinColumns = {
        @JoinColumn(name = "jogador_nickname")},
            inverseJoinColumns = {
                @JoinColumn(name = "artefato_id")})
    private List<Artefato> artefatos;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "tb_patentes", joinColumns = {
        @JoinColumn(name = "jogador_nickname")},
            inverseJoinColumns = {
                @JoinColumn(name = "patente_id")})
    private List<Patente> patentes;

    @OneToMany(mappedBy = "jogador")
    private List<Compra> compras;

    public Jogador() {

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Calendar data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Calendar getData_ultimo_login() {
        return data_ultimo_login;
    }

    public void setData_ultimo_login(Calendar data_ultimo_login) {
        this.data_ultimo_login = data_ultimo_login;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public List<Patente> getPatentes() {
        return patentes;
    }

    public void setPatentes(List<Patente> patentes) {
        this.patentes = patentes;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Artefato> getArtefatos() {
        return artefatos;
    }

    public void setArtefatos(List<Artefato> artefatos) {
        this.artefatos = artefatos;
    }

}
