/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.TipoDecisao;
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
 * @author Elísio Kavaimunwa
 */
public class TipoDecisaoDAO implements GenericoDAO<TipoDecisao>{
    private static final String INSERIR ="INSERT INTO tipo_decisao(tipo_decisao) VALUES (?)";
    private static final String ACTUALIZAR ="UPDATE tipo_decisao set tipo_decisao = ? WHERE id_tipo_decisao = ?";
    private static final String ELIMINAR ="DELETE FROM tipo_decisao WHERE id_tipo_decisao = ?";
    private static final String BUSCAR_POR_CODIGO ="SELECT id_tipo_decisao,tipo_decisao FROM tipo_decisao WHERE id_tipo_decisao = ?";
    private static final String LISTAR_TUDO ="SELECT id_tipo_decisao,tipo_decisao FROM tipo_decisao ORDER BY tipo_decisao ASC;";

    boolean flagControlo = false;
    PreparedStatement ps = null;
    Connection conn = null;
    
    
    @Override
    public boolean save(TipoDecisao tipoDecisao) {
        if (tipoDecisao == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            
            ps.setString(1, tipoDecisao.getTipoDecisao());
            
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
    public boolean update(TipoDecisao tipoDecisao) {
        if (tipoDecisao == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);
            
            ps.setString(1, tipoDecisao.getTipoDecisao());
            ps.setInt(2, tipoDecisao.getIdTipoDecisao());
            
            int retorno = ps.executeUpdate();
            if (retorno > 0) {
                System.out.println("Dados inseridos com sucesso: " + ps.getUpdateCount());
                flagControlo = true;
            }
            return flagControlo;
            
            
        } catch (SQLException e) {
            System.out.println("Erro ao actualizar dados: " + e.getMessage());
            return false;
        } finally {
            Conexao.closeConnection(conn, ps);
        }
    }

    @Override
    public boolean delete(TipoDecisao tipoDecisao) {
         boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (tipoDecisao == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, tipoDecisao.getIdTipoDecisao());
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
    public TipoDecisao findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        TipoDecisao tipoDecisao = new TipoDecisao();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(tipoDecisao, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return tipoDecisao;
    }

    @Override
    public List<TipoDecisao> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoDecisao> tipos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoDecisao tipoDecisao = new TipoDecisao();
                popularComDados(tipoDecisao, rs);
                tipos.add(tipoDecisao);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return tipos;
    }

    @Override
    public void popularComDados(TipoDecisao tipoDecisao, ResultSet rs) {
        try {
            tipoDecisao.setIdTipoDecisao(rs.getInt("id_tipo_decisao"));
            tipoDecisao.setTipoDecisao(rs.getString("tipo_decisao"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
}
