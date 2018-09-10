/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Municipio;
import fenix.iure.modelo.Requerido;
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
public class RequeridoDAO implements GenericoDAO<Requerido> {
    
    private static final String INSERT = "INSERT INTO requerido(nome_requerido, sobrenome_requerido, n_bi_requerido, casa_requerido, rua_requerido, bairro_requerido, id_municipio, id_tipo) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE requerido SET nome_requerido = ? , sobrenome_requerido = ? ,n_bi_requerido=?, casa_requerido=?, rua_requerido=?, bairro_requerido=?,  id_municipio=?, id_tipo=?  WHERE id_requerido = ?";
    private static final String DELETE = "DELETE FROM requerido WHERE id_requerido = ?";
    private static final String SELECT_BY_ID = "SELECT r.nome_requerido, r.sobrenome_requerido, r.n_bi_requerido, r.casa_requerido, r.rua_requerido, r.bairro_requerido, m.nome_municipio, t.nome_tipo FROM requerido r  INNER JOIN municipio m ON r.id_municipio= m.id_municipio INNER JOIN tipo_pessoa t ON r.id_tipo= t.id_tipo WHERE id_requerido = ?";
    private static final String SELECT_ALL = "SELECT r.nome_requerido, r.sobrenome_requerido, r.n_bi_requerido, r.casa_requerido, r.rua_requerido, r.bairro_requerido, m.nome_municipio, t.nome_tipo FROM requerido r  INNER JOIN municipio m ON r.id_municipio= m.id_municipio INNER JOIN tipo_pessoa t ON r.id_tipo= t.id_tipo ORDER BY nome_requerido ASC;";

    @Override
    public boolean save(Requerido requerido) {
          boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerido == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, requerido.getNomeRequerido());
            ps.setString(2, requerido.getSobrenomeRequerido());
            ps.setString(3, requerido.getNbiRequerido());
            ps.setString(4, requerido.getCasaRequerido());
            ps.setString(5, requerido.getRuaRequerido());
            ps.setString(6, requerido.getBairroRequerdo());
            ps.setInt(7, requerido.getIdMunicipio().getIdMunicipio());
            ps.setInt(8, requerido.getIdTipo().getIdTipoPessoa());

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
    public boolean update(Requerido requerido) {
            boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerido == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);

             ps.setString(1, requerido.getNomeRequerido());
            ps.setString(2, requerido.getSobrenomeRequerido());
            ps.setString(3, requerido.getNbiRequerido());
            ps.setString(4, requerido.getCasaRequerido());
            ps.setString(5, requerido.getRuaRequerido());
            ps.setString(6, requerido.getBairroRequerdo());
            ps.setInt(7, requerido.getIdMunicipio().getIdMunicipio());
            ps.setInt(8, requerido.getIdTipo().getIdTipoPessoa());
            ps.setInt(9, requerido.getIdRequerido());
           
            
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
    public boolean delete(Requerido requerido) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (requerido == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
           ps.setInt(1, requerido.getIdRequerido());
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
    public Requerido findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       Requerido requerido = new Requerido();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(requerido, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return requerido;
    }

    @Override
    public List<Requerido> findAll() {
      PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Requerido> requeridos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Requerido requerido = new Requerido();
                popularComDados(requerido, rs);
                requeridos.add(requerido);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return requeridos;
    }

    @Override
    public void popularComDados(Requerido requerido, ResultSet rs) {
         try {
            requerido.setIdRequerido(rs.getInt("id_requerido"));
           requerido.setNomeRequerido(rs.getString("nome_requerido"));
           requerido.setSobrenomeRequerido(rs.getString("sobrenome_requerido"));
           requerido.setNbiRequerido(rs.getString("n_bi_requerido"));
           requerido.setCasaRequerido(rs.getString("casa_requerido"));
           requerido.setRuaRequerido(rs.getString("rua_requerido"));
         requerido.setBairroRequerdo(rs.getString("bairro_requerido"));
           
           
            Municipio municipio = new Municipio();
            municipio.setNomeMunicipio(rs.getString("nome_municpio"));
            requerido.setIdMunicipio(municipio);
            
            TipoPessoa tipoPessoa = new TipoPessoa();
            tipoPessoa.setNomeTipoPessoa(rs.getString("nome_tipo"));
            requerido.setIdTipo(tipoPessoa);

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
        
        
    }
    
}
