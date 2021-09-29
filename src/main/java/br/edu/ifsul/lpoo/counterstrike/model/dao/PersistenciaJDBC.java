package br.edu.ifsul.lpoo.counterstrike.model.dao;

import br.edu.ifsul.lpoo.counterstrike.model.Local;
import br.edu.ifsul.lpoo.counterstrike.model.Objetivo;
import br.edu.ifsul.lpoo.counterstrike.model.Resultado;
import br.edu.ifsul.lpoo.counterstrike.model.Status;
import br.edu.ifsul.lpoo.counterstrike.model.Round;
import br.edu.ifsul.lpoo.counterstrike.model.Modo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Telmo
 */
public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "gustavo@";
    public static final String URL = "jdbc:postgresql://localhost:5432/CounterStrike";

    private Connection con = null;

    public PersistenciaJDBC() throws Exception {

        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com :" + URL + " ..");
        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {

        try {
            if (con != null) {
                return !con.isClosed();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public void fecharConexao() {

        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        if (c == Resultado.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, info_data, status, objetivo_id, round_id from tb_resultado where id = ? ");

            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1

            if (rs.next()) {//se a matriz (ResultSet) tem uma linha

                Resultado p = new Resultado();
                p.setId(rs.getInt("id"));
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                cal.setTime(rs.getDate("data", cal));
                p.setInfo_data(cal);
                p.setStatus(Status.getStatus(rs.getString("status")));

                return p;
            }

        } else if (c == Objetivo.class) {

            Objetivo objetivo = null;

            PreparedStatement ps = this.con.prepareStatement("select p.id, p.descricao, p.pontos" + "from tb_objetivo p" + "where p.id = ?");
            ps.setInt(1, new Integer(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                objetivo = new Objetivo();
                objetivo.setPontos(rs.getInt("pontos"));
                objetivo.setDescricao(rs.getString("descricao"));
                objetivo.setId(rs.getInt("id"));

                ps.close();

                ps = this.con.prepareStatement("select pm.id, pm.nome, pm.latitude, pm.longitude" + "from tb_locais_objetivos ps, tb_local pm" + "where ps.local_id=pm.id and ps.objetivo_id = ?");

                ps.setInt(1, objetivo.getId());

                rs = ps.executeQuery();
                while (rs.next()) {

                    Local p = new Local();
                    p.setId(rs.getInt("id"));
                    p.setLatitude(rs.getString("latitude"));
                    p.setLatitude(rs.getString("longitude"));
                    p.setLatitude(rs.getString("nome"));

                    objetivo.setLocal(p);
                }
                rs.close();
                ps.close();
            }
            return objetivo;

        }
        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

        if (o instanceof Resultado) {
            Resultado r = (Resultado) o;
            if (r.getId() == null) {

                PreparedStatement ps = this.con.prepareStatement("insert into tb_resultado (id, info_data, status, round_id, objetivo_id) values (nextval('seq_resultado_id'), ?, ?, ?, ? )");
                ps.setDate(1, new java.sql.Date(r.getInfo_data().getTimeInMillis()));
                ps.setString(2, r.getStatus().toString());
                ps.setInt(3, r.getRound().getId());
                ps.setInt(4, r.getObjetivo().getId());

                ps.execute();

            } else {

                PreparedStatement ps = this.con.prepareStatement("update tb_resultado set info_data = ?, round_id = ?, objetivo_id = ? where id = ?");
                ps.setDate(1, new java.sql.Date(r.getInfo_data().getTimeInMillis()));
                ps.setString(2, r.getStatus().toString());
                ps.setInt(3, r.getRound().getId());
                ps.setInt(4, r.getObjetivo().getId());
                ps.execute();

            }

        } else if (o instanceof Objetivo) {

            Objetivo objetivo = (Objetivo) o;

            if (objetivo.getId() == null) {

                PreparedStatement ps = this.con.prepareStatement("insert into tb_objetivo (id, descricao, pontos) values (nextval('seq_objetivo_id'), ?, ?) returning id;");
                ps.setString(1, objetivo.getDescricao());
                ps.setInt(2, objetivo.getPontos());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    objetivo.setId(rs.getInt("id"));//seta o id gerado pela sequence.

                    if (objetivo.getLocais() != null && objetivo.getLocais().size() > 0) {

                        for (Local l : objetivo.getLocais()) {

                            ps = this.con.prepareStatement("insert into tb_locais_objetivos (objetivo_id, local_id) values (?, ?)");
                            ps.setInt(1, objetivo.getId());
                            ps.setInt(2, l.getId());

                            //insert em tb_permissoes
                            ps.execute();
                        }
                    }
                }
                rs.close();//fecha o cursor
                ps.close();
            } else {

                //estratégia para alteração: remove tudo e depois insere novamente.
                PreparedStatement ps = this.con.prepareStatement("update tb_objetivo set descricao = ?, pontos = ?, where id = ?");
                ps.setString(1, objetivo.getDescricao());
                ps.setInt(2, objetivo.getPontos());
                ps.execute();

                ps = this.con.prepareStatement("delete from tb_locais_objetivos where objetivo_id = ? ");
                ps.setInt(1, objetivo.getId());
                ps.execute();

                if (objetivo.getLocais() != null && objetivo.getLocais().size() > 0) {

                    for (Local l : objetivo.getLocais()) {

                        ps = this.con.prepareStatement("insert into tb_locais_objetivos (objetivo_id, local_id) values (?, ?)");
                        ps.setInt(1, objetivo.getId());
                        ps.setInt(2, l.getId());

                        //insert em tb_permissoes
                        ps.execute();
                    }

                    ps.close();
                }
            }

        } else if (o instanceof Round){
                      Round round = (Round) o;

                PreparedStatement ps = this.con.prepareStatement("insert into tb_round (id, data_inicio, data_fim, modo) values (nextval('seq_round_id'), ?, ?, ?) returning id; ");
                ps.setDate(1, new java.sql.Date(round.getData_inicio().getTimeInMillis()));
                ps.setDate(2, new java.sql.Date(round.getData_fim().getTimeInMillis()));
                ps.setString(3, round.getModo().toString());

                //insert em tb_perfil
                ResultSet rs = ps.executeQuery();

                
                ps.close();

        }

    }

    @Override
    public void remover(Object o) throws Exception {

        if (o instanceof Resultado) {

            Resultado p = (Resultado) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_resultado where id = ?");
            ps.setInt(1, p.getId());
            ps.execute();

            ps.close();

        }
        if (o instanceof Objetivo) {

            //delete em tb_locais_objetivos
            Objetivo objetivo = (Objetivo) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_locais_objetivos where objetivo_id = ? ");
            ps.setInt(1, objetivo.getId());
            ps.execute();

            //delete em tb_objetivo.
            ps = this.con.prepareStatement("delete from tb_objetivo where id = ? ");
            ps.setInt(1, objetivo.getId());
            ps.execute();

            ps.close();

        }

    }

    @Override
    public List<Resultado> getResultados() throws Exception {

        List<Resultado> lista = null;
        PreparedStatement ps = this.con.prepareStatement("select id, data, status from tb_resultado order by id asc");
        ResultSet rs = ps.executeQuery();
        lista = new ArrayList();
        while (rs.next()) {

            Resultado p = new Resultado();
            p.setId(rs.getInt("id"));
            Calendar cal = Calendar.getInstance();
            cal.getTime();
            cal.setTime(rs.getDate("data", cal));
            p.setInfo_data(cal);
            p.setStatus(Status.getStatus(rs.getString("status")));

            PreparedStatement ps2 = this.con.prepareStatement("select pm.id, pm.data_inicio, pm.data_fim, pm.modo "
                    + " from tb_round ps, tb_resultado pm "
                    + " where pm.round_id = ps.id");
            ResultSet rs2 = ps2.executeQuery();
            Round f = new Round();
            f.setId(rs2.getInt("id"));
            Calendar cal2 = Calendar.getInstance();
            cal.getTime();
            cal.setTime(rs2.getDate("data_inicio", cal2));
            f.setData_inicio(cal2);
            Calendar cal3 = Calendar.getInstance();
            cal3.getTime();
            cal3.setTime(rs2.getDate("data_fim", cal3));
            f.setData_fim(cal3);
            f.setModo(Modo.getModo(rs2.getString("modo")));
            p.setRound(f);

            PreparedStatement ps3 = this.con.prepareStatement("select pm.id, pm.descricao, pm. "
                    + " from tb_objetivo ps, tb_resultado pm "
                    + " where pm.objetivo_id = ps.id");
            ResultSet rs3 = ps3.executeQuery();
            Objetivo j = new Objetivo();
            j.setId(rs3.getInt("id"));
            j.setDescricao(rs3.getString("descricao"));
            j.setPontos(rs3.getInt("pontos"));
            
            p.setObjetivo(j);

            lista.add(p);
        }

        return lista;
    }

    @Override
    public List<Objetivo> getObjetivos() throws Exception {

        List<Objetivo> lista = null;

        PreparedStatement ps
                = this.con.prepareStatement("select p.id, p.descricao, p.pontos "
                        + " from tb_Objetivo p "
                        + " order by p.id asc ");
        ResultSet rs = ps.executeQuery();//executa a query

        lista = new ArrayList();

        while (rs.next()) {

            Objetivo objetivo = new Objetivo();//inicialização do objeto que será retornado.
            objetivo.setId(rs.getInt("id"));
            objetivo.setDescricao(rs.getString("descricao"));
            objetivo.setPontos(rs.getInt("pontos"));

            // executar um select envolvendo tb_permissoes e tb_permissao.
            PreparedStatement ps2 = this.con.prepareStatement("select pm.id, pm.nome, pm.latitude, pm.longitude "
                    + " from tb_tb_locais_objetivos ps, tb_local pm "
                    + " where ps.local_id=pm.id and  ps.objetivo_id = ? ");
            ps2.setInt(1, objetivo.getId());

            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {

                Local f = new Local();
                f.setId(rs2.getInt("id"));
                f.setNome(rs2.getString("nome"));
                f.setLatitude(rs2.getString("latitude"));
                f.setLongitude(rs2.getString("longitude"));

                objetivo.setLocal(f);//adiciona no perfil a permissao                    
            }
            rs2.close();//fecha o cursor
            ps2.close();//fecha

            lista.add(objetivo);
        }

        rs.close();
        ps.close();//fecha o cursor

        return lista;

    }

}
