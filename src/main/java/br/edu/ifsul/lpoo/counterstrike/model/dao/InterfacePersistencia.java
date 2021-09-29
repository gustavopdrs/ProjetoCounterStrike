/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model.dao;


import br.edu.ifsul.lpoo.counterstrike.model.Objetivo;
import br.edu.ifsul.lpoo.counterstrike.model.Resultado;
import br.edu.ifsul.lpoo.counterstrike.model.Round;
import java.util.List;

public interface InterfacePersistencia {
    
    public Boolean conexaoAberta();//prot�tipos
    
    public void fecharConexao();
    
    public Object find(Class c, Object id) throws Exception;
    
    public void persist(Object o) throws Exception;
    
    public void remover(Object o) throws Exception;

    public List<Objetivo> getObjetivos() throws Exception;
    
    public List<Resultado> getResultados() throws Exception;
    
//    public List<Produto> getProdutos() throws Exception;
            
}
