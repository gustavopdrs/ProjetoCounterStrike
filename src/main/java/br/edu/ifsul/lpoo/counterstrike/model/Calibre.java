/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model;

/**
 *
 * @author gugag
 */

public enum Calibre {
    
    MM03(new Float(0.3)), MM05(new Float(0.5)), MM08(new Float(0.8));
    
    private float valor;
    
    Calibre(float valor) {
        this.valor = valor;
    }
    
    public float getValor() {
        return valor;
    }
    
        public static Calibre getCalibre(String nameEnum){
        
        if(nameEnum.equals(Calibre.MM03.toString()))
            
            return Calibre.MM03;
        
        else if(nameEnum.equals(Calibre.MM05.toString())){
            
            return Calibre.MM05;
            
        }else if(nameEnum.equals(Calibre.MM08.toString())){
            
            return Calibre.MM08;
            
        }else{
            return null;
        }
    }
    
}

