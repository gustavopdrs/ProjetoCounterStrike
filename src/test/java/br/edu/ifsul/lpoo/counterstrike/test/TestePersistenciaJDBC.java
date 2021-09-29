/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.counterstrike.test;

import br.edu.ifsul.lpoo.counterstrike.model.Modo;
import br.edu.ifsul.lpoo.counterstrike.model.Resultado;
import br.edu.ifsul.lpoo.counterstrike.model.Status;
import br.edu.ifsul.lpoo.counterstrike.model.Round;
import br.edu.ifsul.lpoo.counterstrike.model.Objetivo;
import br.edu.ifsul.lpoo.counterstrike.model.Local;
import br.edu.ifsul.lpoo.counterstrike.model.Partida;
import br.edu.ifsul.lpoo.counterstrike.model.dao.PersistenciaJDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import org.junit.Test;

/**
 *
 * @author gugag
 */
public class TestePersistenciaJDBC {

    @Test
    public void testarConexaoJDBC() throws Exception {

        PersistenciaJDBC conexaoJDBC = new PersistenciaJDBC();

        if (conexaoJDBC.conexaoAberta()) {

            System.out.println("Conexão Aberta com sucesso");
            conexaoJDBC.fecharConexao();

        } else {
            System.out.println("Conexao Recusada");
        }
    }

    @Test
    public void testeJDBC() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        int aux = 1;
        int aux2 = 0;
        if (persistencia.conexaoAberta()) {
            boolean test = true;
            System.out.println("Conexão Aberta com sucesso");
            for (aux = 0; test != false; aux++) {
                Resultado r = (Resultado) persistencia.find(Resultado.class, new Integer(aux));
                if (r == null) {
                    r = new Resultado();
                    System.out.println("Não existem resultados cadastrados");
                    test = false;
                    r.setId(aux);
                    Calendar cal = Calendar.getInstance();
                    cal.getTime();
                    r.setInfo_data(cal);
                    r.setStatus(Status.SIM);

                    Round o = new Round();
                    o.setId(aux);
                    Calendar cal2 = Calendar.getInstance();
                    cal2.getTime();
                    o.setData_fim(cal2);
                    Calendar cal3 = Calendar.getInstance();
                    cal3.getTime();
                    o.setData_inicio(cal);
                    o.setModo(Modo.TERRORISTA);

                    r.setRound(o);

                    Objetivo jj = new Objetivo();
                     jj.setId(aux);
                    jj.setDescricao("Plantar a bomba");
                    jj.setPontos(50);

                    for (int i = 0; i < 3; i++) {
                        Local l = new Local();
                        l.setId(i);
                        l.setNome("Local " + i);
                        l.setLatitude("25 XY 3" + i);
                        l.setLongitude("28 LC 5" + i);
                        jj.setLocal(l);
                    }

                    persistencia.persist(jj);

                    r.setObjetivo(jj);

                    persistencia.persist(o);

                    persistencia.persist(r);
                    break;
                } else {
                    persistencia.getResultados();
                    persistencia.remover(r);
                }
            }

            for (aux2 = 0; test != false; aux2++) {
                Objetivo j = (Objetivo) persistencia.find(Objetivo.class, new Integer(aux));
                if (j == null) {
                    System.out.println("Não existem objetivos cadastrados");
                    j.setId(aux);
                    j.setDescricao("Plantar a bomba");
                    j.setPontos(50);

                    for (int i = 0; i < 3; i++) {
                        Local ll = new Local();
                        ll.setId(i);
                        ll.setNome("Local " + i);
                        ll.setLatitude("25 XY 3" + i);
                        ll.setLongitude("28 LC 5" + i);
                        j.setLocal(ll);
                    }

                    persistencia.persist(j);
                    break;
                } else {
                    persistencia.getObjetivos();
                    persistencia.remover(j);
                }
            }

            persistencia.fecharConexao();

        } else {
            System.out.println("Conexao Recusada");
        }

    }
}
