/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerente;
import fenix.iure.modelo.TipoPessoa;
import fenix.iure.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aisha Lubadika
 */
public class RequerenteDAO implements GenericoDAO<Requerente> {

    private static final String INSERT = "INSERT INTO requente(nome_requente, sobrenome_requerente, n_bi_requerente, casa_requerente, rua_requerente, bairro_requerente, id_municipio, id_tipo) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE requente SET nome_requente = ? , sobrenome_requerente = ? ,n_bi_requerente=?, casa_requerente=?, rua_requerente=?, bairro_requerente=?,  id_municipio=?, id_tipo=?  WHERE id_requente = ?";
    private static final String DELETE = "DELETE FROM requente WHERE id_requente = ?";
    private static final String SELECT_BY_ID = "SELECT r.id_requente,r.nome_requente, r.sobrenome_requerente, r.n_bi_requerente, r.casa_requerente, r.rua_requerente, r.bairro_requerente, m.nome_municipio, t.nome_tipo FROM requente r  INNER JOIN municipio m ON r.id_municipio= m.id_municipio INNER JOIN tipo_pessoa t ON r.id_tipo= t.id_tipo WHERE id_requente = ?";
    private static final String SELECT_ALL = "SELECT r.id_requente, r.nome_requente, r.sobrenome_requerente, r.n_bi_requerente, r.casa_requerente, r.rua_requerente, r.bairro_requerente, m.nome_municipio, t.nome_tipo FROM requente r  INNER JOIN municipio m ON r.id_municipio= m.id_municipio INNER JOIN tipo_pessoa t ON r.id_tipo= t.id_tipo ORDER BY nome_requente ASC;";

    @Override
    public boolean save(Requerente requerente) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerente == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, requerente.getNomeRequerente());
            ps.setString(2, requerente.getSobrenomeRequerente());
            ps.setString(3, requerente.getNbiRequerente());
            ps.setString(4, requerente.getCasaRequerente());
            ps.setString(5, requerente.getRuaRequerente());
            ps.setString(6, requerente.getBairroRequerente());
            ps.setInt(7, requerente.getMunicipio().getIdMunicipio());
            ps.setInt(8, requerente.getIdTipo().getIdTipoPessoa());

            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean update(Requerente requerente) {
       boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerente == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, requerente.getNomeRequerente());
            ps.setString(2, requerente.getSobrenomeRequerente());
            ps.setString(3, requerente.getNbiRequerente());
            ps.setString(4, requerente.getCasaRequerente());
            ps.setString(5, requerente.getRuaRequerente());
            ps.setString(6, requerente.getBairroRequerente());
            ps.setInt(7, requerente.getMunicipio().getIdMunicipio());
            ps.setInt(8, requerente.getIdTipo().getIdTipoPessoa());
            ps.setInt(9, requerente.getIdRequerente());
           
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados actualizar com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean delete(Requerente requerente) {
           boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerente == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
           ps.setInt(1, requerente.getIdRequerente());
          int retorno = ps.executeUpdate();

            if (retorno > 0) {
                System.out.println("Dados eliminados com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }

            return flagControlo;

        } catch (Exception ex) {
            System.err.println("Erro ao eliminar dados: " + ex.getLocalizedMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
            {
            }
        }
    }

    @Override
    public Requerente findById(Integer id) {
         PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Requerente requerente = new Requerente();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(requerente, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return requerente;
    }

    @Override
    public List<Requerente> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Requerente> requerentes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Requerente requerente = new Requerente();
                popularComDados(requerente, rs);
                requerentes.add(requerente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return requerentes;
    }

   

    @Override
    public void popularComDados(Requerente requerente, ResultSet rs) {
           try {
           requerente.setIdRequerente(rs.getInt("id_requente"));
           requerente.setNomeRequerente(rs.getString("nome_requente"));
           requerente.setSobrenomeRequerente(rs.getString("sobrenome_requerente"));
           requerente.setNbiRequerente(rs.getString("n_bi_requerente"));
           requerente.setCasaRequerente(rs.getString("casa_requerente"));
           requerente.setRuaRequerente(rs.getString("rua_requerente"));
           requerente.setBairroRequerente(rs.getString("bairro_requerente"));
           
           
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municipio"));
            requerente.setMunicipio(municipio);
            
            TipoPessoa tipoPessoa = new TipoPessoa();
            tipoPessoa.setNomeTipoPessoa(rs.getString("nome_tipo"));
            requerente.setIdTipo(tipoPessoa);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
        
    }

}
