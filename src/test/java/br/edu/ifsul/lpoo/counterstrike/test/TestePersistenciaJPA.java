/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.test;

import br.edu.ifsul.lpoo.counterstrike.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author gugag
 */
public class TestePersistenciaJPA {
    
    /**
     *
     * @throws Exception
     */
    @Test
    public void testarConexao() throws Exception{
        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        
        if(persistencia.conexaoAberta())
        {
            System.out.println("Conexao com o BD aberta utilizando JPA");
            persistencia.fecharConexao();
        }else{
            System.err.println("Não abriu conexao via jpa");
        }
        
    }
  
    
}
