/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Provincia;
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

public class TipoPessoaDAO implements GenericoDAO<TipoPessoa>{
    
    private static final String INSERIR ="INSERT INTO tipo_pessoa(nome_tipo) VALUES (?)";
    private static final String ACTUALISAR ="UPDATE tipo_pessoa set nome_tipo = ? WHERE id_tipo = ?";
    private static final String ELIMINAR ="DELETE FROM tipo_pessoa WHERE id_tipo = ?";
    private static final String BUSCAR_POR_CODIGO ="SELECT id_tipo,nome_tipo FROM tipo_pessoa WHERE id_tipo = ?";
    private static final String LISTAR_TUDO ="SELECT id_tipo,nome_tipo FROM tipo_pessoa ORDER BY nome_tipo ASC;";
    
    boolean flagControlo = false;
    PreparedStatement ps = null;
    Connection conn = null;


    @Override
    public boolean save(TipoPessoa tipoPessoa) {
        if (tipoPessoa == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);
            
            ps.setString(1, tipoPessoa.getNomeTipoPessoa());
            
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
    public boolean update(TipoPessoa tipoPessoa) {
        if (tipoPessoa == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALISAR);
            
            ps.setString(1, tipoPessoa.getNomeTipoPessoa());
            ps.setInt(2, tipoPessoa.getIdTipoPessoa());
            
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
    public boolean delete(TipoPessoa tipoPessoa) {
         boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (tipoPessoa == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, tipoPessoa.getIdTipoPessoa());
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
    public TipoPessoa findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        TipoPessoa tipoPessoa = new TipoPessoa();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(tipoPessoa, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return tipoPessoa;
    }

    @Override
    public List<TipoPessoa> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoPessoa> tipos = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoPessoa tipoPessoa = new TipoPessoa();
                popularComDados(tipoPessoa, rs);
                tipos.add(tipoPessoa);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return tipos;
    }


    @Override
    public void popularComDados(TipoPessoa tipoPessoa, ResultSet rs) {
         try {
            tipoPessoa.setIdTipoPessoa(rs.getInt("id_tipo"));
            tipoPessoa.setNomeTipoPessoa(rs.getString("nome_tipo"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
}
