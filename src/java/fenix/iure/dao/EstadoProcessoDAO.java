/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.EstadoProcesso;
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
public class EstadoProcessoDAO implements GenericoDAO<EstadoProcesso>{
     private static final String INSERT ="INSERT INTO estado_processo(estado_processo ) VALUES (?)";
    private static final String UPDATE ="UPDATE estado_processo SET especie_processo WHERE id_especie_processo=?";
    private static final String DELETE ="DELETE FROM estado_procesoo WHERE id_estado_processo=?";
    private static final String SELECT_BY_ID ="SELECT id_estado_processo, estado_processo FROM estado_processo WHERE id_estado_processo = ?";
    private static final String SELECT_ALL ="SELECT id_estado_processo, estado_processo FROM estado_processo;";

    @Override
    public boolean save(EstadoProcesso estadoProcesso) {
         boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (estadoProcesso == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERT);

            ps.setString(1, estadoProcesso.getEstadoProcesso());
           
            
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
    public boolean update(EstadoProcesso estadoProcesso) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (estadoProcesso == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(UPDATE);

            ps.setString(1, estadoProcesso.getEstadoProcesso());
            ps.setInt(1, estadoProcesso.getIdEstadoProcesso());
           
            
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
    public boolean delete(EstadoProcesso estadoProcesso) {
                 boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (estadoProcesso == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1,estadoProcesso.getIdEstadoProcesso());
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
    public EstadoProcesso findById(Integer id) {
         PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
       EstadoProcesso estadoProcesso = new EstadoProcesso();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(estadoProcesso, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return estadoProcesso;
    }

    @Override
    public List<EstadoProcesso> findAll() {
         PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<EstadoProcesso> estadoProcessos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(SELECT_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                EstadoProcesso estadoProcesso = new EstadoProcesso();
                popularComDados(estadoProcesso, rs);
                estadoProcessos.add(estadoProcesso);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return estadoProcessos;
    }

    @Override
    public void popularComDados(EstadoProcesso estadoProcesso, ResultSet rs) {
         try {
            estadoProcesso.setIdEstadoProcesso(rs.getInt("id_estado_processo"));
            estadoProcesso.setEstadoProcesso(rs.getString("estado_processo"));
          

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }

    
    
}
