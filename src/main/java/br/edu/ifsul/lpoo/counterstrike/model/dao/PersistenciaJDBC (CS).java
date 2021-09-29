//package br.edu.ifsul.lpoo.counterstrike.model.dao;
//
//
//import br.edu.ifsul.lpoo.counterstrike.model.Local;
//import br.edu.ifsul.lpoo.counterstrike.model.Objetivo;
//import br.edu.ifsul.lpoo.counterstrike.model.Resultado;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.xml.bind.DatatypeConverter;
//
///**
// *
// * @author Telmo
// */
//public class PersistenciaJDBC implements InterfacePersistencia {
//
//    private final String DRIVER = "org.postgresql.Driver";
//    private final String USER = "postgres";
//    private final String SENHA = "gustavo@";
//    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//
//    private Connection con = null;
//
//    public PersistenciaJDBC() throws Exception {
//
//        Class.forName(DRIVER);
//        System.out.println("Tentando estabelecer conexao JDBC com :" + URL + " ..");
//        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
//
//    }
//
//    @Override
//    public Boolean conexaoAberta() {
//
//        try {
//            if (con != null) {
//                return !con.isClosed();
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//
//    }
//
//    @Override
//    public void fecharConexao() {
//
//        try {
//            this.con.close();
//            System.out.println("Fechou conexao JDBC");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public Object find(Class c, Object id) throws Exception {
//
//				return null;
//            }
//
//        
//       
//
//    @Override
//    public void persist(Object o) throws Exception {
//
//     
//        } if(o instanceof Objetivo) {
//
//            Objetivo objetivo = (Objetivo) o;
//
//            if (objetivo.getId() == null) {
//
//                PreparedStatement ps = this.con.prepareStatement("insert into tb_objetivo (id, descricao, pontos) values (nextval('seq_objetivo_id'), ?, ?) returning id;");
//                ps.setString(1, objetivo.getDescricao());
//                ps.setInt(2, objetivo.getPontos());
//
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//
//                    objetivo.setId(rs.getInt("id"));//seta o id gerado pela sequence.
//
//                    if (objetivo.getLocais() != null && objetivo.getLocais().size() > 0) {
//
//                        for (Local l : objetivo.getLocais()) {
//
//                            ps = this.con.prepareStatement("insert into tb_local (objetivo_id, local_id) values (?, ?)");
//                            ps.setInt(1, objetivo.getId());
//                            ps.setInt(2, l.getId());
//
//                            //insert em tb_permissoes
//                            ps.execute();
//                        }
//                    }
//                }
//                rs.close();//fecha o cursor
//                ps.close();
//            } else {
//
//                //estratégia para alteração: remove tudo e depois insere novamente.
//                PreparedStatement ps = this.con.prepareStatement("update tb_objetivo set descricao = ?, pontos = ?, where id = ?");
//                ps.setString(1, objetivo.getDescricao());
//                ps.setInt(2, objetivo.getPontos());
//                ps.execute();
//
//                ps = this.con.prepareStatement("delete from tb_local where objetivo_id = ? ");
//                ps.setInt(1, objetivo.getId());
//                ps.execute();
//
//                if (objetivo.getLocais() != null && objetivo.getLocais().size() > 0) {
//
//                    for (Local l : objetivo.getLocais()) {
//
//                        ps = this.con.prepareStatement("insert into tb_local (objetivo_id, local_id) values (?, ?)");
//                        ps.setInt(1, objetivo.getId());
//                        ps.setInt(2, l.getId());
//
//                        //insert em tb_permissoes
//                        ps.execute();
//                    }
//
//                    ps.close();
//                }
//            }
//
//        } else if (o instanceof Resultado) {
//            Resultado r = (Resultado) o;
//            if (r.getId() == null) {
//
//                PreparedStatement ps = this.con.prepareStatement("insert into tb_resultado (id, data, status, round, objetivo) values (nextval('seq_resultado_id'), ?, ?, ?, ? )");
//                ps.setTime(1, null, r.getData());
//                ps.setENUM(2, r.getStatus()); /* !!!!!! */
//
//                ResultSet rs = ps.executeQuery();
//                if (rs.next()) {
//
//                    r.setId(rs.getInt("id"));//seta o id gerado pela sequence.
//
//                    if (r.getRound() != null) {
//
//                            ps = this.con.prepareStatement("insert into tb_round (resultado_id, round_id) values (?, ?)");
//                            ps.setInt(1, r.getId());
//                            ps.setInt(2, r.getRound().getId());
//
//                            ps.execute();
//                        
//                    }
//                    if (r.getObjetivo()!= null) {
//                            ps = this.con.prepareStatement("insert into tb_objetivo (resultado_id, objetivo_id) values (?, ?)");
//                            ps.setInt(1, r.getId());
//                            ps.setInt(2, r.getObjetivo().getId());
//
//                            ps.execute();
//                        
//                    }
//                }
//                ps.execute();
//
//            } else {
//
//                PreparedStatement ps = this.con.prepareStatement("update tb_pais set nome = ? where id = ?");
//                ps.setString(1, p.getNome());
//                ps.setInt(2, p.getId());
//                ps.execute();
//
//            }
//        }
//
//    }
//
//    @Override
//    public void remover(Object o) throws Exception {
//
//     
//
//    }
//
//    
//
//}
