/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EspecieProcesso;
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
public class EspecieProcessoDAO implements GenericoDAO<EspecieProcesso> {
    
    private static final String INSERT ="INSERT INTO especie_processo(especie_processo ) VALUES (?)";
    private static final String UPDATE ="UPDATE especie_processo SET especie_processo=? WHERE id_especie_processo=?";
    private static final String DELETE ="DELETE FROM especie_procesoo WHERE id_especie_processo=?";
    private static final String SELECT_BY_ID ="SELECT id_especie_processo,especie_processo FROM especie_processo WHERE id_especie_processo=?";
    private static final String SELECT_ALL ="SELECT id_especie_processo,especie_processo FROM especie_processo";

    @Override
    public boolean save(EspecieProcesso especieProcesso) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (especieProcesso == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);
            ps.setString(1, especieProcesso.getEspecieProcesso());
           
            
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
    public boolean update(EspecieProcesso especieProcesso) {
       boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (especieProcesso == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, especieProcesso.getEspecieProcesso());
            ps.setInt(2, especieProcesso.getIdEspecieProcesso());
           
            
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
    public boolean delete(EspecieProcesso especieProcesso) {
         boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (especieProcesso == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1,especieProcesso.getIdEspecieProcesso());
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
    public EspecieProcesso findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       EspecieProcesso especieProcesso = new EspecieProcesso();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(especieProcesso, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return especieProcesso;
    }

    @Override
    public List<EspecieProcesso> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<EspecieProcesso> especieProcessos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                EspecieProcesso especieProcesso = new EspecieProcesso();
                popularComDados(especieProcesso, rs);
                especieProcessos.add(especieProcesso);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return especieProcessos;
    }

    @Override
    public void popularComDados(EspecieProcesso especieProcesso, ResultSet rs) {
        try {
            especieProcesso.setIdEspecieProcesso(rs.getInt("id_especie_processo"));
            especieProcesso.setEspecieProcesso(rs.getString("especie_processo"));
          

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
}
