/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.model.dao;
import br.edu.ifsul.lpoo.counterstrike.model.Objetivo;
import br.edu.ifsul.lpoo.counterstrike.model.Resultado;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author gugag
 */
public class PersistenciaJPA implements InterfacePersistencia{
  
    public EntityManagerFactory factory;
    public EntityManager entity;
    
    public PersistenciaJPA()
    {
        factory = Persistence.createEntityManagerFactory("pu_db_CounterStrike_2021");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        
        return entity.isOpen();
        
    }

    @Override
    public void fecharConexao() {
        
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        return entity.find(c, id);
    }
    
    /**
     *
     * @param c
     * @param name
     * @return
     * @throws Exception
     */
    public Object findName(Class c, String name) throws Exception {
        return entity.find(c, name);
    }
        
    @Override
    public void persist(Object o) throws Exception {
        
        entity.getTransaction().begin();
        entity.persist(o);
        entity.getTransaction().commit();
    }

    @Override
    public void remover(Object o) throws Exception {
        
        entity.getTransaction().begin();
        entity.remove(o);
        entity.getTransaction().commit();
    }

    @Override
    public List<Objetivo> getObjetivos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Resultado> getResultados() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
