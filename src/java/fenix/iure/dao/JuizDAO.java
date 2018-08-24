/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.iure.dao;

import fenix.iure.modelo.Juiz;
import fenix.iure.modelo.Provincia;
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
public class JuizDAO implements GenericoDAO<Juiz>{
    
    private static final String INSERIR ="INSERT INTO juiz(nome_juiz,sobrenome_juiz,data_nascimento_juiz,data_inicio_funcoes ) VALUES (?,?,?,?)";
    private static final String ACTUALIZAR ="UPDATE juiz set nome_juiz=?, sobrenome_juiz=?,data_nascimento_juiz=?, data_inicio_funcoes=?  WHERE id_juiz=?";
    private static final String ELIMINAR ="DELETE FROM juiz WHERE id_juiz=?";
    private static final String BUSCAR_POR_CODIGO ="SELECT id_juiz,nome_juiz,sobrenome_juiz,data_nascimento_juiz, data_inicio_funcoes FROM juiz WHERE id_juiz = ?";
    private static final String LISTAR_TUDO ="SELECT id_juiz,nome_juiz,sobrenome_juiz,data_nascimento_juiz, data_inicio_funcoes FROM juiz ORDER BY nome_juiz ASC;";

    
    @Override
    public boolean save(Juiz juiz) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (juiz == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(INSERIR);

            ps.setString(1, juiz.getNomeJuiz());
            ps.setString(2, juiz.getSobreNomeJuiz());
            ps.setDate(3, new java.sql.Date(juiz.getDataNascimento().getTime()));
            ps.setDate(4, new java.sql.Date(juiz.getDataInicioFuncoes().getTime()));
            
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
    public boolean update(Juiz juiz) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (juiz == null) {
            System.err.println("O valor oassado não pode ser nulo!");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ACTUALIZAR);

            ps.setString(1, juiz.getNomeJuiz());
            ps.setString(2, juiz.getSobreNomeJuiz());
            ps.setDate(3, new java.sql.Date(juiz.getDataNascimento().getTime()));
            ps.setDate(4, new java.sql.Date(juiz.getDataInicioFuncoes().getTime()));
            ps.setInt(5, juiz.getIdJuiz());
            
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
    public boolean delete(Juiz juiz) {
        boolean flagControlo = false;
        PreparedStatement ps = null;
        Connection conn = null;
        if (juiz == null) {
            System.err.println("O valor passado nao pode ser nulo");
        }
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(ELIMINAR);
            ps.setInt(1, juiz.getIdJuiz());
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
    public Juiz findById(Integer id) {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Juiz juiz = new Juiz();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(BUSCAR_POR_CODIGO);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.err.println("Não foi encontrado nenhum registo com o id: " + id);
            }
            popularComDados(juiz, rs);
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn, ps, rs);
        }
        return juiz;
    }

    @Override
    public List<Juiz> findAll() {
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Juiz> juizes = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            ps = conn.prepareStatement(LISTAR_TUDO);
            rs = ps.executeQuery();
            while (rs.next()) {
                Juiz juiz = new Juiz();
                popularComDados(juiz, rs);
                juizes.add(juiz);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao ler dados: " + ex.getLocalizedMessage());
        } finally {
            Conexao.closeConnection(conn);
        }
        return juizes;
    }

    @Override
    public void popularComDados(Juiz juiz, ResultSet rs) {
        try {
            juiz.setIdJuiz(rs.getInt("id_juiz"));
            juiz.setNomeJuiz(rs.getString("nome_juiz"));
            juiz.setSobreNomeJuiz(rs.getString("sobrenome_juiz"));
            juiz.setDataNascimento(rs.getDate("data_nascimento_juiz"));
            juiz.setDataInicioFuncoes(rs.getDate("data_inicio_funcoes"));

        } catch (SQLException ex) {
            System.err.println("Erro ao carregar dados: " + ex.getLocalizedMessage());
        }
    }
    
}
